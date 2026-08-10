# Starts Postgres (if needed), backend JAR, Cloudflare tunnel, and publishes
# the tunnel URL to breimato.es/cuaderno/runtime-config.json via FTP.
#
# Designed to run at Windows logon (Scheduled Task).

$ErrorActionPreference = 'Stop'

$repoRoot = Split-Path -Parent (Split-Path -Parent $PSScriptRoot)
$backendRoot = Join-Path $repoRoot 'backend'
$frontendRoot = Join-Path $repoRoot 'frontend'
$logsDir = Join-Path $backendRoot 'scripts\logs'
$jarPath = Join-Path $backendRoot 'target\expenses-0.0.1-SNAPSHOT.jar'
$cloudflared = 'C:\Program Files (x86)\cloudflared\cloudflared.exe'
$javaCmd = Get-Command java -ErrorAction SilentlyContinue
if ($javaCmd) {
    $javaExe = $javaCmd.Source
} else {
    $javaExe = 'java'
}

New-Item -ItemType Directory -Force -Path $logsDir | Out-Null

$backendOut = Join-Path $logsDir 'backend.out.log'
$backendErr = Join-Path $logsDir 'backend.err.log'
$tunnelOut = Join-Path $logsDir 'tunnel.out.log'
$tunnelErr = Join-Path $logsDir 'tunnel.err.log'
$stackLog = Join-Path $logsDir 'stack.log'

function Write-StackLog([string]$message) {
    $line = "[{0}] {1}" -f (Get-Date -Format 'yyyy-MM-dd HH:mm:ss'), $message
    Add-Content -Path $stackLog -Value $line
    Write-Host $line
}

function Test-PortOpen([int]$port) {
    try {
        $client = New-Object System.Net.Sockets.TcpClient
        $async = $client.BeginConnect('127.0.0.1', $port, $null, $null)
        $ok = $async.AsyncWaitHandle.WaitOne(500)
        if ($ok -and $client.Connected) {
            $client.Close()
            return $true
        }
        $client.Close()
        return $false
    } catch {
        return $false
    }
}

function Wait-Port([int]$port, [int]$timeoutSeconds) {
    $deadline = (Get-Date).AddSeconds($timeoutSeconds)
    while ((Get-Date) -lt $deadline) {
        if (Test-PortOpen $port) {
            return $true
        }
        Start-Sleep -Seconds 1
    }
    return $false
}

function Ensure-Postgres {
    $service = Get-Service -Name 'postgresql-x64-18' -ErrorAction SilentlyContinue
    if (-not $service) {
        Write-StackLog 'WARNING: servicio postgresql-x64-18 no encontrado'
        return
    }
    if ($service.Status -ne 'Running') {
        Write-StackLog 'Arrancando PostgreSQL...'
        Start-Service postgresql-x64-18
    } else {
        Write-StackLog 'PostgreSQL ya en marcha'
    }
}

function Ensure-Backend {
    if (Test-PortOpen 8080) {
        Write-StackLog 'Backend ya escucha en :8080'
        return
    }
    if (-not (Test-Path $jarPath)) {
        Write-StackLog "Compilando JAR (no existe $jarPath)..."
        Push-Location $backendRoot
        try {
            mvn -DskipTests package
        } finally {
            Pop-Location
        }
    }
    if (-not (Test-Path $jarPath)) {
        throw "No se encontró el JAR del backend: $jarPath"
    }

    Write-StackLog 'Arrancando backend...'
    $env:DB_HOST = 'localhost'
    $env:DB_PORT = '5432'
    $env:DB_NAME = 'expenses_db'
    $env:DB_USERNAME = 'expenses_user'
    $env:DB_PASSWORD = 'expenses_pass'

    Start-Process -FilePath $javaExe `
        -ArgumentList @('-jar', $jarPath) `
        -WorkingDirectory $backendRoot `
        -WindowStyle Hidden `
        -RedirectStandardOutput $backendOut `
        -RedirectStandardError $backendErr `
        -PassThru | Out-Null

    if (-not (Wait-Port 8080 90)) {
        throw 'El backend no abrió el puerto 8080 a tiempo. Revisa scripts/logs/backend.err.log'
    }
    Write-StackLog 'Backend OK en :8080'
}

function Stop-PreviousTunnel {
    Get-CimInstance Win32_Process -Filter "Name = 'cloudflared.exe'" -ErrorAction SilentlyContinue |
        Where-Object { $_.CommandLine -match 'tunnel --url' } |
        ForEach-Object {
            Write-StackLog "Cerrando túnel previo PID $($_.ProcessId)"
            Stop-Process -Id $_.ProcessId -Force -ErrorAction SilentlyContinue
        }
}

function Start-TunnelAndPublish {
    if (-not (Test-Path $cloudflared)) {
        throw "cloudflared no encontrado en $cloudflared"
    }

    Stop-PreviousTunnel
    Remove-Item $tunnelOut, $tunnelErr -ErrorAction SilentlyContinue

    Write-StackLog 'Arrancando túnel Cloudflare...'
    Start-Process -FilePath $cloudflared `
        -ArgumentList @('tunnel', '--url', 'http://127.0.0.1:8080') `
        -WindowStyle Hidden `
        -RedirectStandardOutput $tunnelOut `
        -RedirectStandardError $tunnelErr `
        -PassThru | Out-Null

    $tunnelUrl = $null
    $deadline = (Get-Date).AddSeconds(90)
    while ((Get-Date) -lt $deadline) {
        foreach ($logFile in @($tunnelErr, $tunnelOut)) {
            if (Test-Path $logFile) {
                $match = Select-String -Path $logFile -Pattern 'https://[a-zA-Z0-9-]+\.trycloudflare\.com' -ErrorAction SilentlyContinue |
                    Select-Object -Last 1
                if ($match) {
                    $tunnelUrl = [regex]::Match($match.Line, 'https://[a-zA-Z0-9-]+\.trycloudflare\.com').Value
                    break
                }
            }
        }
        if ($tunnelUrl) {
            break
        }
        Start-Sleep -Seconds 1
    }

    if (-not $tunnelUrl) {
        throw 'No se obtuvo URL del túnel. Revisa scripts/logs/tunnel.err.log'
    }

    Write-StackLog "Túnel listo: $tunnelUrl"
    Write-StackLog 'Publicando runtime-config.json en SiteGround...'
    Push-Location $frontendRoot
    try {
        node .\scripts\upload-runtime-config.mjs $tunnelUrl
    } finally {
        Pop-Location
    }
    Write-StackLog 'Stack expenses operativo'
}

Ensure-Postgres
Ensure-Backend
Start-TunnelAndPublish
