#!/bin/bash
# run.sh - Compila y ejecuta la aplicación final

cd ..

echo "[1/2] Compilando Backend..."
cd backend
./mvnw clean package -DskipTests

echo "[2/2] Iniciando Devhelper..."
java -jar target/backend-1.0.0-SNAPSHOT.jar
