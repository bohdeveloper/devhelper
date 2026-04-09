# dev.ps1 - Inicia Devhelper en modo dev en Windows
Set-Location ..\backend

Write-Host "Iniciando Devhelper en modo Desarrollo..." -ForegroundColor Yellow
.\mvnw.cmd spring-boot:run
