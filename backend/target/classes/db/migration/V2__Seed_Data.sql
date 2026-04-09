-- Seed users
INSERT INTO users (username, password, role) VALUES ('admin', 'admin', 'ROLE_ADMIN');

-- Seed snippets
INSERT INTO snippets (name, language, tags, content) VALUES
('Hello World JS', 'javascript', 'js,basic', 'console.log("Hello World");'),
('Spring Boot Controller', 'java', 'spring,java,controller', '@RestController\npublic class HelloController {\n    @GetMapping("/")\n    public String hello() { return "Hello"; }\n}'),
('Bootstrap Navbar', 'html', 'bootstrap,html,ui', '<nav class="navbar navbar-expand-lg navbar-light bg-light">...</nav>');

-- Seed entities
INSERT INTO entities (id, name, description) VALUES (1, 'Product', 'Producto para tienda virtual');

-- Seed entity fields
INSERT INTO entity_fields (entity_id, name, type, nullable, primary_key) VALUES
(1, 'id', 'Long', 0, 1),
(1, 'name', 'String', 0, 0),
(1, 'price', 'BigDecimal', 0, 0);

-- Seed oracle objects
INSERT INTO oracle_objects (name, type, definition) VALUES ('USERS_SEQ', 'SEQUENCE', 'CREATE SEQUENCE USERS_SEQ START WITH 1 INCREMENT BY 1;');

-- Seed ai prompts
INSERT INTO ai_prompts (title, prompt_text, category) VALUES
('Formulario Bootstrap', 'Genera un formulario HTML usando Bootstrap 5 para crear o editar un recurso con los siguientes campos: [campos]. Incluye validación de frontend básica.', 'Frontend'),
('Tabla DataTables', 'Genera código HTML y JS para inicializar una tabla DataTables con AJAX cargando desde el endpoint [url]. Las columnas deben ser [columnas].', 'Frontend'),
('CRUD Spring Boot', 'Genera una clase Entity JPA, un Repository Spring Data, un Service y un RestController para la entidad [entidad] con los campos [campos]. Usar Lombok y validaciones Jakarta.', 'Backend'),
('DDL Oracle desde JSON', 'Convierte esta estructura JSON de modelo de datos en scripts DDL compatibles con Oracle Database 19c. [json_model]', 'Base de Datos');
