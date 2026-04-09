# User Guide - Devhelper

Bienvenido a la Guía de Usuario de **Devhelper**. Esta aplicación está diseñada para no interponerse en tu camino, y estar siempre disponible en `http://localhost:8080` de tu máquina mientras trabajas.

## 1. Login

Toda la herramienta, por seguridad, se encuentra detrás de Spring Security protegiéndolo de que no se exponga si corres algún túnel. 
Utiliza el usuario **admin** y clave **admin**.

## Tema Oscuro (Dark Mode)
Devhelper soporta nativamente el Modo Oscuro. Puedes cambiar entre el modo claro y oscuro usando el icono de la luna/sol ubicado en la esquina superior derecha de la barra de navegación. Tu preferencia se guardará automáticamente en el navegador.

## 2. Herramientas Principales

### Gestor de Snippets
El Dashboard inicial te dará paso al gestor de Fragmentos de Código.
1. Haz click en Nuevo Snippet.
2. Ingresa un Título, Lenguaje (Sirve de identificador visual) y Categorización de Tags por comas.
3. Copia el código fuente que usas siempre.
4. Al visualizarlo, puedes usar el botón de portapapeles incrustado.

### Generador Frontend
Genera de manera visual un bloque HTML compatible con Bootstrap 5. Puedes obtener:
- Tablas con configuraciones DataTables listas para consumir endpoints.
- Formularios insertables en modales que mapean campos por nombre.

### Modelador Backend (GenEntity)
Si necesitas desarrollar un nuevo microservicio, en lugar de lidiar con boilerplate:
1. Dirígete a **Modelador Backend** > **Nueva Entidad**.
2. Dale un nombre en PascalCase (Ej. `Factura`).
3. Agrega todos los campos. Marca los PK y la opción de ser nulo o no nulo (Esto es para `@Column(nullable = false)`).
4. Dale a guardar.
5. Luego usa los botones para exportar su Capa de Dominio a `.java`: Entidad JPA, Controller, Service, DTO, Repositorio.
6. **Testing**: También puedes emplear los botones `ServiceTest` y `ControllerTest` para generar automáticamente esqueletos de tests unitarios basados en JUnit 5 y Mockito para la entidad, cubriendo todo el CRUD.

### Oracle Toolkit
Ideal para evitar conectarse a un DB Client para trazar una estructura básica o un script de parche.
El valor agregado es que la UI volcará a tu disco cada versión del Script con el sufijo `_TIPO.sql`, lo que facilita que un repositorio Git pueda rastrear cambios históricos.
