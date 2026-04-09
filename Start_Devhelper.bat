@echo off
title Devhelper - Servidor
cd /d "%~dp0"

echo ========================================================
echo             DEVHELPER - HERRAMIENTA LOCAL
echo ========================================================

cd backend

:: si hay una instancia anterior en ejecución, intentar matarla antes de compilar
for /f "tokens=1" %%I in ('jps -v ^| find "-Dapp=devhelper"') do (
    echo [INFO] Matando proceso Devhelper PID %%I antes de compilar...
    taskkill /PID %%I /F >nul 2>&1
)

:: borrar cualquier target residual para evitar bloqueos de OneDrive/antivirus
if exist "target" (
    echo [INFO] Forzando borrado de directorio target...
    rmdir /s /q "target" 2>nul || (
        echo [WARN] No fue posible eliminar target. comprobar que no esta bloqueado. 
    )
)

:: siempre recompilar para reflejar cambios en src
echo [INFO] Compilando proyecto (clean package)...
call mvnw.cmd clean package -DskipTests

:: esto garantiza que el jar esté actualizado incluso si ya existía

echo [INFO] Iniciando servidor Spring Boot...
echo [INFO] Se abrira una ventana en tu navegador automaticamente.
echo.
echo ========================================================
echo [INSTRUCCIONES PARA PARAR]
echo Para DETENER la aplicacion, simplemente CIERRA esta ventana,
echo usa el archivo Stop_Devhelper.bat en otra ventana,
echo o presiona Ctrl+C.
echo ========================================================
echo.

:: Se lanza el navegador en segundo plano tras 8 segundos de espera
start /b cmd /c "timeout /t 8 >nul & start http://localhost:8080/"

:: comprobar que el jar existe antes de intentar arrancar
if not exist "target\devhelper-1.0.0.jar" (
    echo [ERROR] No se ha generado el jar. Revisa los mensajes de error anteriores.
    pause
    exit /b 1
)

:: Inicia la app bloqueando esta consola para ver los logs
java -Dapp=devhelper -jar target\devhelper-1.0.0.jar
