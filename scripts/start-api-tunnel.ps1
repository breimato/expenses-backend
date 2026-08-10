# Exposes local backend (http://127.0.0.1:8080) with a public HTTPS URL via Cloudflare.
# Keep this running while using https://breimato.es/cuaderno/
#
# Usage:
#   .\scripts\start-api-tunnel.ps1
#
# Then put the printed https://....trycloudflare.com URL into frontend/.env.deploy.local
# as VITE_API_URL and redeploy the frontend if the URL changed.

$ErrorActionPreference = 'Stop'

$cloudflared = 'C:\Program Files (x86)\cloudflared\cloudflared.exe'
if (-not (Test-Path $cloudflared)) {
    throw 'cloudflared no encontrado. Instálalo con: winget install Cloudflare.cloudflared'
}

Write-Host 'Túnel → http://127.0.0.1:8080'
Write-Host 'Cuando aparezca la URL https://....trycloudflare.com, úsala como VITE_API_URL y redespliega el frontend si cambió.'
Write-Host 'Ctrl+C para parar.'
Write-Host ''

& $cloudflared tunnel --url http://127.0.0.1:8080
