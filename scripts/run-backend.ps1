# Runs the expenses backend against native PostgreSQL (no Docker).
# Usage:
#   .\scripts\run-backend.ps1
#
# Optional:
#   $env:DB_PORT = '5432'
#   $env:JWT_SECRET = '...'

$ErrorActionPreference = 'Stop'

$backendRoot = Split-Path -Parent $PSScriptRoot
Set-Location $backendRoot

if (-not $env:DB_HOST) { $env:DB_HOST = 'localhost' }
if (-not $env:DB_PORT) { $env:DB_PORT = '5432' }
if (-not $env:DB_NAME) { $env:DB_NAME = 'expenses_db' }
if (-not $env:DB_USERNAME) { $env:DB_USERNAME = 'expenses_user' }
if (-not $env:DB_PASSWORD) { $env:DB_PASSWORD = 'expenses_pass' }

Write-Host "Backend → jdbc:postgresql://${env:DB_HOST}:${env:DB_PORT}/${env:DB_NAME}"
Write-Host "Ctrl+C para parar."
Write-Host ""

mvn -DskipTests spring-boot:run
