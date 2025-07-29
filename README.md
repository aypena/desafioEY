# DesafíoEy API 

## Descripción
Una API REST construida en Java con Spring Boot, que sigue la **Arquitectura Limpia** (Clean Architecture)
con capas de presentación, dominio y datos, utiliza H2 como base de datos en memoria y Gradle como herramienta de construcción.

## Tecnologías
- Java 17
- Spring Boot 3.5.4 (Web, JPA, Validation, Security)
- JWT (jjwt‑api, jjwt‑impl, jjwt‑jackson)
- H2 Database (en memoria)
- Lombok
- Bouncy Castle (criptografía)
- Gradle (build tool)
- JUnit 5 + Mockito para pruebas

## Como desacargar el proyecto Proyecto.
1. Clonar el repositorio desde GitHub
Ve a la página del repositorio en GitHub:
https://github.com/aypena/desafioEY/tree/main

Haz clic en el botón Code y copia la URL (HTTPS o SSH).

Abre tu terminal (Git Bash / Terminal / PowerShell).

Ejecuta el comando:

bash
Copy
Edit
git clone https://github.com/aypena/desafioEY.git
Esto creará una carpeta llamada desafioEY con todos los archivos del proyecto localmente 
docs.github.com
.

2. Abrir el proyecto en IntelliJ IDEA
Inicia IntelliJ IDEA.

Selecciona File → Open... (o Open Project).

Navega hasta la carpeta desafioEY que acabas de clonar y ábrela.

IntelliJ detectará automáticamente que es un proyecto Gradle. Acepta la sugerencia de importar como proyecto Gradle.

Se descargará el classpath y las dependencias definidas en build.gradle.

3. Ejecutar la aplicación
Con el IDE configurado:

En IntelliJ, busca la clase con el método main(...) que arranca Spring Boot (normalmente en src/main/java/...).

## Como ejecutar el Postman
1. Configurar puerto en application.properties (server.port=8081)  esto ya lo tiene predefinido el proyecto.
2. Iniciar aplicación Postman
3. En Postman: crear un nuevo proyecto
   - Método: POST
   - URL: http://localhost:8081/api/v1/users/register
   - Headers:
       Content-Type: application/json
       Accept: application/json
   - Body :
   - {
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2",
"phones": [
{
"number": "1234567",
"citycode": "1",
"contrycode": "57"
}
]
}

4. Enviar y capturar el token JWT de la respuesta


## Estructura del Proyecto
src/
├── presentation
│ ├── UserController
│ └── DTOs (UserRequestDTO, UserResponseDTO)
├── domain
│ ├── CreateUserUseCase
│ └── modelos (User)
└── data
├── UserRepository (interfaz)
└── persistencia (Spring JPA)

## Flujo de Solicitud (Request Flow)
1. El cliente envía una petición HTTP.
2. `UserController` recibe y convierte en DTO.
3. `CreateUserUseCase` aplica la lógica de negocio.
4. `UserRepository` persiste en la base H2.
5. Al crear usuario, `TokenGenerator` genera JWT.
6. La respuesta HTTP regresa con el token JWT generado.

## La API correrá en http://localhost:8081/api/v1/users/register

## Json de prueba entrada
{
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2",
"phones": [
{
"number": "1234567",
"citycode": "1",
"contrycode": "57"
}
]
}

## Jsos de respuesta Salida Esperada

{
    "id": "415482c3-5fb1-4b96-ad6e-44c4a6fb052b",
    "create": "2025-07-29T15:08:06.183559",
    "modified": "2025-07-29T15:08:06.183559",
    "lastLogin": "2025-07-29T15:08:06.183559",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI4OTEzMTc5YS04MWU0LTRlMzYtOGUzMC0xZGI2MWJkZjE5ZjQiLCJlbWFpbCI6InVnZGs1Z0BndHNqZ2ZqZHNnZi5uaCIsImlhdCI6MTc1MzgxNjA4NiwiZXhwIjoxNzUzODE5Njg2fQ.RqSX34uUzeauvjEWZAU5lXFCDBivzRiRqgil6q28DJQ",
    "active": true
}

## Json de Salida para errores 
{
    "mensaje": "El correo ya registrado"
}


