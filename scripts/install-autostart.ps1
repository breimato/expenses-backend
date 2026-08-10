# Installs autostart without admin rights via the user Startup folder.

$ErrorActionPreference = 'Stop'

$scriptPath = Join-Path $PSScriptRoot 'start-expenses-stack.ps1'
$startupDir = Join-Path $env:APPDATA 'Microsoft\Windows\Start Menu\Programs\Startup'
$cmdPath = Join-Path $startupDir 'ExpensesHomeStack.cmd'

New-Item -ItemType Directory -Force -Path $startupDir | Out-Null

@(
    '@echo off'
    "powershell.exe -NoProfile -ExecutionPolicy Bypass -WindowStyle Hidden -File `"$scriptPath`""
) | Set-Content -Path $cmdPath -Encoding ASCII

Write-Host "Autostart instalado (carpeta Inicio del usuario):"
Write-Host "  $cmdPath"
Write-Host ""
Write-Host "Se ejecutará al iniciar sesión. Para probar ahora:"
Write-Host "  powershell -NoProfile -ExecutionPolicy Bypass -File `"$scriptPath`""
