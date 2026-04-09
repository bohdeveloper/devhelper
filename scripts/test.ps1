# test.ps1 - Ejecuta pruebas en Windows
Set-Location ..\backend

Write-Host "Ejecutando suite de pruebas JUnit 5..." -ForegroundColor Cyan
.\mvnw.cmd test
