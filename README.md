# ManagementApp 🛍️

Aplicación full-stack para la gestión de tareas, compuesta por un backend en Java Spring Boot, un frontend en Vue.js y una base de datos PostgreSQL. Todo el sistema está completamente dockerizado para facilitar su ejecución y despliegue.

## 🚀 Tecnologías utilizadas

- Backend: Java + Spring Boot 4.0.2 + Spring Security + Spring Data JPA + Spring Web MVC
- Frontend: Vue.js + Vite
- Base de datos: PostgreSQL (con pgvector)
- Contenedores: Docker + Docker Compose
- Documentación automática de API: SpringDoc OpenAPI 2.8.6 - Swagger
- Autenticación y Seguridad: JWT (JSON Web Tokens)

## 📦 Arquitectura

El proyecto está dividido en tres servicios principales:
- backend → API REST que gestiona las tareas y se documenta mediante Swagger
- frontend → Aplicación web para interactuar con la API
- postgres → Base de datos persistente

Todos los servicios están conectados mediante una red Docker.

## 🌐 Documentación

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs


## ⚙️ Requisitos

Antes de ejecutar el proyecto necesitas tener instalado:
- Docker
- Docker Compose

## 🐳 Ejecución con Docker

Para construir y levantar toda la aplicación, desde la raíz del proyecto::
- ```docker-compose up --build```


## 🗄️ Base de datos

La base de datos se inicializa automáticamente mediante un script:
- ```docker-config/database/init.sql```

Además, los datos se persisten usando un volumen Docker:
- ```postgres_data```

## 🔐 Consideraciones de seguridad

- Comunicación entre contenedores mediante red bridge privada.
- Variables de entorno para credenciales.
- Base de datos no expuesta innecesariamente en entornos productivos.
- Nginx como servidor estático para el frontend.
