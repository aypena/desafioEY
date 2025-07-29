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

La API correrá en http://localhost:8081/api/v1/users/register

Json de prueba entrada
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

Jsos de respuesta Salida Esperada

{
    "id": "415482c3-5fb1-4b96-ad6e-44c4a6fb052b",
    "create": "2025-07-29T15:08:06.183559",
    "modified": "2025-07-29T15:08:06.183559",
    "lastLogin": "2025-07-29T15:08:06.183559",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI4OTEzMTc5YS04MWU0LTRlMzYtOGUzMC0xZGI2MWJkZjE5ZjQiLCJlbWFpbCI6InVnZGs1Z0BndHNqZ2ZqZHNnZi5uaCIsImlhdCI6MTc1MzgxNjA4NiwiZXhwIjoxNzUzODE5Njg2fQ.RqSX34uUzeauvjEWZAU5lXFCDBivzRiRqgil6q28DJQ",
    "active": true
}

Json de Salida para errores 
{
    "mensaje": "El correo ya registrado"
}


