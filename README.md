# ManagementApp 🛍️

Aplicación full-stack para la gestión de tareas, compuesta por un backend en Java Spring Boot, un frontend en Vue.js y una base de datos PostgreSQL. Todo el sistema está completamente dockerizado para facilitar su ejecución y despliegue.

## 🚀 Tecnologías utilizadas

- Backend: Java + Spring Boot
- Frontend: Vue.js + Vite
- Base de datos: PostgreSQL (con pgvector)
- Contenedores: Docker + Docker Compose

## 📦 Arquitectura

El proyecto está dividido en tres servicios principales:
- backend → API REST que gestiona las tareas
- frontend → Aplicación web para interactuar con la API
- postgres → Base de datos persistente

Todos los servicios están conectados mediante una red Docker.

## 🔐 Consideraciones de seguridad

- Comunicación entre contenedores mediante red bridge privada.
- Variables de entorno para credenciales.
- Base de datos no expuesta innecesariamente en entornos productivos.
- Nginx como servidor estático para el frontend.

## ⚙️ Requisitos

Antes de ejecutar el proyecto necesitas tener instalado:
- Docker
- Docker Compose

## 🐳 Ejecución con Docker

Para construir y levantar toda la aplicación, desde la raíz del proyecto::
- ```docker-compose up --build```

## 🌐 Acceso a la aplicación

Una vez levantado el entorno:
- Frontend → http://localhost
- Backend API → http://localhost:8080/api/tasks

## 🗄️ Base de datos

La base de datos se inicializa automáticamente mediante un script:
- ```docker-config/database/init.sql```

Además, los datos se persisten usando un volumen Docker:
- ```postgres_data```
