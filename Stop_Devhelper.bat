@echo off
title Devhelper - Parar Servidor
echo ========================================================
echo             DETENIENDO DEVHELPER
echo ========================================================
echo Buscando proceso de Devhelper en memoria...
set FOUND=0

for /f "tokens=1" %%I in ('jps -v ^| find "-Dapp=devhelper"') do (
    echo Matando proceso PID %%I...
    taskkill /PID %%I /F
    set FOUND=1
)

if %FOUND%==0 (
    echo [INFO] No se encontro ningun proceso de Devhelper en ejecucion.
) else (
    echo.
    echo [OK] Devhelper se ha detenido correctamente.
    :: borrar el jar para forzar recompilación en el próximo start
    if exist "backend\target\backend-1.0.0-SNAPSHOT.jar" (
        echo [INFO] Eliminando artefacto viejo...
        del /q "backend\target\backend-1.0.0-SNAPSHOT.jar"
    )
)
timeout /t 3 >nul
