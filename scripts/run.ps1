# run.ps1 - Compila y ejecuta la aplicación en Windows
Set-Location "$PSScriptRoot\.."

Write-Host "[1/2] Compilando Backend..." -ForegroundColor Cyan
Set-Location backend
.\mvnw.cmd clean package -DskipTests

Write-Host "[2/2] Iniciando Devhelper..." -ForegroundColor Green
java -jar target\backend-1.0.0-SNAPSHOT.jar
