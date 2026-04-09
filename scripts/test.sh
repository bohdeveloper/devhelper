#!/bin/bash
# test.sh - Ejecuta las pruebas del sistema

cd ../backend
echo "Ejecutando suite de pruebas JUnit 5..."
./mvnw test
