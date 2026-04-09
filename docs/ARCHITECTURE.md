# Arquitectura del Sistema (Architecture Notes)

Devhelper no usa Docker, ni un entorno en la nube, ni complejas colas de mensajes debido a su naturaleza: Ser un **Portable Executable**.

## 1. Patrón Singleton de Aplicación
Buscamos que levante y corra sobre una máquina sin instalaciones adicionales más que Java (JRE o JDK).

## 2. ¿Por qué SQLite Local?
En vez de recurrir a H2 Web Console, las configuraciones productivas usan un `.db` file guardado siempre en tu `C:\Users\tuusuario\.devhelper\` de manera que el estado sobreviva reinstalaciones y apagones sin configuraciones extras.
Flyway procesa el dialecto usando el driver `org.xerial:sqlite-jdbc` estándar validando versiones V1 y V2.

## 3. Storage Service - Inyección a Sistema
Cuando un desarrollador hace una generación o quiere exportar un Oracle SQL, no se fuerza la descarga web únicamente. Spring Boot usando `java.nio.file.Files;` escribe directo sobre el directorio local /storage o /oracle-toolkit donde lanzaste la app para lograr un workflow donde el DEV tiene el IDE abierto pegado a la consola viendo los archivos nuevos aparecer automáticamente.

## 4. Templating Engine Independiente (Freemarker)
Se eligió a Thymeleaf únicamente como el Renderizador V (MVC). 
La razón para incorporar Freemarker como Engine es separar intencionalmente las plantillas web dinámicas (Dashboard, index...) de las **plantillas estáticas** de generadores de Código (El output de Java, HTML y SQL). Así se evita colisión de sintaxis de `${}` que tiene Thymeleaf con el lenguaje destino generado.
