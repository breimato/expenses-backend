# Creates expenses_db + expenses_user on native PostgreSQL (no Docker).
# Usage:
#   $env:PGPASSWORD = 'tu-password-de-postgres'
#   .\scripts\setup-native-db.ps1
#
# Optional overrides:
#   $env:PGUSER = 'postgres'
#   $env:PGHOST = 'localhost'
#   $env:PGPORT = '5432'

$ErrorActionPreference = 'Stop'

$psql = 'C:\Program Files\PostgreSQL\18\bin\psql.exe'
if (-not (Test-Path $psql)) {
    $found = Get-ChildItem 'C:\Program Files\PostgreSQL' -Recurse -Filter psql.exe -ErrorAction SilentlyContinue |
        Select-Object -First 1 -ExpandProperty FullName
    if (-not $found) {
        throw 'No se encontró psql.exe. Instala PostgreSQL o ajusta la ruta en este script.'
    }
    $psql = $found
}

$pgUser = if ($env:PGUSER) { $env:PGUSER } else { 'postgres' }
$pgHost = if ($env:PGHOST) { $env:PGHOST } else { 'localhost' }
$pgPort = if ($env:PGPORT) { $env:PGPORT } else { '5432' }

if (-not $env:PGPASSWORD) {
    $secure = Read-Host -AsSecureString 'Password del usuario postgres'
    $bstr = [Runtime.InteropServices.Marshal]::SecureStringToBSTR($secure)
    $env:PGPASSWORD = [Runtime.InteropServices.Marshal]::PtrToStringAuto($bstr)
}

$dbName = 'expenses_db'
$dbUser = 'expenses_user'
$dbPass = 'expenses_pass'

Write-Host "Conectando a PostgreSQL en ${pgHost}:${pgPort} como ${pgUser}..."

& $psql -U $pgUser -h $pgHost -p $pgPort -d postgres -v ON_ERROR_STOP=1 -c "SELECT 1" | Out-Null

$roleExists = & $psql -U $pgUser -h $pgHost -p $pgPort -d postgres -tAc "SELECT 1 FROM pg_roles WHERE rolname = '$dbUser'"
if ($roleExists.Trim() -ne '1') {
    Write-Host "Creando rol $dbUser..."
    & $psql -U $pgUser -h $pgHost -p $pgPort -d postgres -v ON_ERROR_STOP=1 `
        -c "CREATE ROLE $dbUser LOGIN PASSWORD '$dbPass';"
} else {
    Write-Host "Rol $dbUser ya existe."
}

$dbExists = & $psql -U $pgUser -h $pgHost -p $pgPort -d postgres -tAc "SELECT 1 FROM pg_database WHERE datname = '$dbName'"
if ($dbExists.Trim() -ne '1') {
    Write-Host "Creando base $dbName..."
    & $psql -U $pgUser -h $pgHost -p $pgPort -d postgres -v ON_ERROR_STOP=1 `
        -c "CREATE DATABASE $dbName OWNER $dbUser;"
} else {
    Write-Host "Base $dbName ya existe."
}

& $psql -U $pgUser -h $pgHost -p $pgPort -d $dbName -v ON_ERROR_STOP=1 -c @"
GRANT ALL ON SCHEMA public TO $dbUser;
ALTER SCHEMA public OWNER TO $dbUser;
"@

Write-Host ""
Write-Host "Listo. Credenciales para el backend:"
Write-Host "  DB_HOST=$pgHost"
Write-Host "  DB_PORT=$pgPort"
Write-Host "  DB_NAME=$dbName"
Write-Host "  DB_USERNAME=$dbUser"
Write-Host "  DB_PASSWORD=$dbPass"
Write-Host ""
Write-Host "Arranca el API con: .\scripts\run-backend.ps1"
