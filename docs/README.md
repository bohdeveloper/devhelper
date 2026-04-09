# Devhelper

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com/tu-usuario/devhelper)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

**Devhelper** es una herramienta local de productividad de tipo "navaja suiza" diseñada para desarrolladores web y de backend. Su cometido es ejecutarse 100% aislada de servicios cloud interactuando como una aplicación web nativa (Spring Boot MVC + SQLite) para acelerar la creación de código, scripts de BBDD, diagramación de entidades SQL, generación de DTOs y snippets de código reutilizable. 

## Características

* **Gestor de Snippets (Módulo 1)**: CRUD local de fragmentos de código categorizados mediante tags y coloreados en monospacio, con soporte para copia al portapapeles.
* **Generadores Frontend (Módulo 2)**: Creación dinámica de maquetación HTML Boostrap 5 lista para usar, formularios CRUD enlazados con Spring MVC, y templates de tablas DataTables con jQuery y peticiones AJAX.
* **Modelador Backend (Módulo 3)**: Diseñador visual de entidades para BBDD. Añade columnas dinámicamente y expórtalas de forma segmentada (`Entity.java`, `Repository`, `Service`, `Controller`, `DTO`). Todo el motor funciona mediante plantillas Freemarker exportándolas físicamente al almacén o directas a descarga del navegador.
* **Oracle Toolkit (Módulo 4)**: Librería robusta local para generar y salvaguardar DDLs de Oracle (Tablas, Vistas, triggers, procedimientos, secuencias). Cada vez que guardas, guarda una copia `.sql` dentro del directorio `oracle-toolkit`.
* **IA Helper (Módulo 5)**: Interfaz que sirve los prompts más depurados y óptimos para interactuar con IAs Generativas en contexto de arquitectura Spring Boot / JPA y UI.
* **Privacidad Total**: Tu código nunca sale de tu PC. Las bases de datos migran sin servicios externos y todos los logs quedan en `~/.devhelper`.

## Estructura del Proyecto

Al utilizar una arquitectura "Monorepo", **Devhelper** se compone de las siguientes carpetas en la raíz:
- `/backend`: Aplicación y código fuente de Spring Boot 3 (Java 21).
- `/db`: Carpeta expuesta diseñada para migraciones secundarias.
- `/oracle-toolkit`: Almacén donde caen en texto plano todos los scripts SQL generados en el Módulo 4.
- `/storage`: Ficheros exportados de java o logs del sistema de template.
- `/scripts`: Shell scripts (`.sh`) para Linux/Mac y Powershell (`.ps1`) para Windows usados para gestionar el entorno.
- `/docs`: Documentación markdown en profundidad.

## Requisitos

- Java 21 (JDK instalado y configurado en `$JAVA_HOME`).
- Maven (Opcional, el proyecto incluye un Wrapper para Windows, Mac y Linux).
- Un navegador web moderno web (Chrome, Edge, Firefox).

## Instalación y Arranque Rápido (Windows)

La forma más rápida y sencilla de gestionar Devhelper en Windows es usando los accesos directos de la raíz del proyecto:

1. **Doble clic en `Start_Devhelper.bat`:** Este script se encargará de compilar la aplicación (si es su primera vez), levantar el servidor en segundo plano, y abrirá automáticamente tu navegador en la ruta correcta.
2. No cierres la ventana negra (consola) que se abre mientras quieras usar la aplicación, ya que es el servidor ejecutándose.

> **Alternativa manual:** Puedes ejecutar `.\scripts\run.ps1` desde Powershell.

## Instalación y Arranque Rápido (Linux / Mac)
```bash
git clone https://github.com/tu-usuario/devhelper.git
cd devhelper
chmod +x scripts/*.sh

# Arranque base de Spring Boot con Maven Wrapper
./scripts/run.sh
```

Una vez finalice la build, la aplicación estará expuesta en el panel de `http://localhost:8080/`. El login está capado de serie por un único usuario duro:
- **Usuario**: admin 
- **Password**: admin

## Parada de la Aplicación

- **Windows**: Para detener la aplicación, simplemente cierra la ventana de la consola (negra) o ejecuta el archivo **`Stop_Devhelper.bat`**. 
- **Mac/Linux**: Simplemente vuelve a la consola o terminal desde donde la ejecutaste pulsando `Ctrl + C`. Esto detendrá el proceso de Java de forma limpia.

## Documentación Detallada

- [Guía de Usuario](./USER_GUIDE.md) - Documentación para operar Devhelper.
- [Arquitectura Técnica](./ARCHITECTURE.md) - Decisiones de diseño.

## Licencia

Este proyecto se distribuye bajo [Licencia MIT](LICENSE). Siéntete libre de modificar, forkear o extender.
