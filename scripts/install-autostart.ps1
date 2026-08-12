# Registers start-expenses-stack.ps1 to run at Windows logon (Scheduled Task, no admin).

$ErrorActionPreference = 'Stop'

$scriptPath = Join-Path $PSScriptRoot 'start-expenses-stack.ps1'
$taskName = 'ExpensesHomeStack'
$startupCmd = Join-Path $env:APPDATA 'Microsoft\Windows\Start Menu\Programs\Startup\ExpensesHomeStack.cmd'

if (-not (Test-Path $scriptPath)) {
    throw "No se encontró el script: $scriptPath"
}

$action = New-ScheduledTaskAction `
    -Execute 'powershell.exe' `
    -Argument "-NoProfile -ExecutionPolicy Bypass -WindowStyle Hidden -File `"$scriptPath`""

$trigger = New-ScheduledTaskTrigger -AtLogOn -User $env:USERNAME

$settings = New-ScheduledTaskSettingsSet `
    -AllowStartIfOnBatteries `
    -DontStopIfGoingOnBatteries `
    -StartWhenAvailable `
    -MultipleInstances IgnoreNew

Register-ScheduledTask `
    -TaskName $taskName `
    -Action $action `
    -Trigger $trigger `
    -Settings $settings `
    -Description 'Arranca PostgreSQL, backend, túnel Cloudflare y publica runtime-config al iniciar sesión.' `
    -Force | Out-Null

# Evitar doble ejecución si quedó un acceso directo antiguo en Inicio.
Remove-Item -Path $startupCmd -ErrorAction SilentlyContinue

Write-Host "Autostart instalado (tarea programada al iniciar sesión):"
Write-Host "  Nombre: $taskName"
Write-Host "  Script: $scriptPath"
Write-Host ""
Write-Host "Para probar ahora:"
Write-Host "  Start-ScheduledTask -TaskName '$taskName'"
Write-Host ""
Write-Host "Para desinstalar:"
Write-Host "  Unregister-ScheduledTask -TaskName '$taskName' -Confirm:`$false"
