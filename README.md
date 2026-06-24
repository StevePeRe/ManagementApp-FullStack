# ManagementApp 🛍️

Aplicación full-stack para la gestión de tareas, compuesta por un backend en Java Spring Boot, un frontend en Vue.js y una base de datos PostgreSQL. Todo el sistema está completamente dockerizado para facilitar su ejecución y despliegue. Las tareas pueden ser escritas en lenguaje natural para luego ser pasadas mediante analisis de IA a un formato legible por el backend. Posee un modo fallback en caso de que los servicios de Ollama no estén disponibles o no se prefiera el uso del mismo.

## 🚀 Tecnologías utilizadas

- Backend: Java + Spring Boot 3.4.5 + Spring Security + Spring AI + Spring Data JPA + Spring Web MVC
- Frontend: Vue.js + Vite
- Base de datos: PostgreSQL (con pgvector)
- Contenedores: Docker + Docker Compose
- LLMs: Ollama
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

Uso de Ollama
- Docker Desktop corriendo.
- Mínimo 8GB RAM en la máquina.
- Espacio en disco: ~4GB para descargar el modelo llama3.2.

## 🤖 Inteligencia Artificial (Opcional)

Este proyecto integra IA local mediante **Spring AI + Ollama** para analizar tareas en lenguaje natural. 
Sin embargo, está diseñado para funcionar **sin dependencias externas** mediante un motor de reglas local (fallback).

### Modos de Ejecución

| Modo | Comando | Descripción | Requisitos |
|------|---------|-------------|------------|
| **🚀 Ligero (Default)** | `docker-compose up` | Usa análisis local por reglas. Ideal para demos rápidas. | Docker Desktop |
| **🧠 IA Completa** | `docker-compose --profile ai up` | Usa modelo LLM `llama3.2` para análisis semántico real. | Docker + 8GB RAM |


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
