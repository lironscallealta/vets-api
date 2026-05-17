# Vets Microservice (vets-api v1)

## Descripción

Microservicio encargado de la **gestión de veterinarios y su disponibilidad** dentro del ecosistema de VetDistribuidora SPA. Permite registrar a los profesionales veterinarios, configurar sus horarios y disponibilidad, y gestionar las consultas agendadas.

Este microservicio forma parte de una arquitectura distribuida para la transformación digital de VetDistribuidora SPA.

## Tech Stack

### Infraestructura:

- [Java 25 LTS](https://docs.oracle.com/en/java/javase/25/): Última versión Java LTS.
- [Spring Boot v4.0.6](https://github.com/spring-projects/spring-boot): Última versión estable.
- [Docker](https://docs.docker.com/) & [Docker Compose](https://docs.docker.com/compose/): Contenedorización y entorno de desarrollo.
- [MySQL v8.4 LTS](https://hub.docker.com/_/mysql): Base de datos relacional.

### Dependencias:

1. **Lombok:** Reducción de boilerplate
2. **Validation:** Validación de beans
3. **Spring Boot DevTools:** Autoreload
4. **Spring WebMVC:** Capacidades REST
5. **Spring WebFlux:** Para comunicación inter-microservicios
6. **Spring Data JPA:** ORM para manejo de entidades
7. **MySQL Connector:** Driver de base de datos
8. **Flyway Migration:** Migraciones versionadas
9. **Spotless (Palantir):** Autoformateador de código

## Estructura del Proyecto

```
src/main/java/cl/duoc/vets_api/
├── VetsApiApplication.java
├── controller/                      # Endpoints REST
├── dto/                             # DTOs request/response
├── model/                           # Entidades JPA (Veterinario, Disponibilidad, etc.)
├── repository/                      # Repositorios JPA
└── service/                         # Lógica de negocio
```

## Entorno de Desarrollo

### 1. Configurar variables de entorno

Crear un archivo `.env` a partir del ejemplo proporcionado:

```bash
cp .env.example .env
```

Variables principales del `.env`:

```yaml
SPRING_ENV=dev
SPRING_APP_NAME=VetsMicroservice
HOST_PORT=8083
HOST_DB_PORT=3308
MYSQL_DATABASE=vets
MYSQL_HOST=localhost
MYSQL_USER=user
MYSQL_PASSWORD=password
MYSQL_ROOT_PASSWORD=root_password
PHPMYADMIN_PORT=8092
```

### 2. Levantar la base de datos

```bash
docker compose up -d
```

### 3. Verificar BD vía phpMyAdmin

- Ir a [http://localhost:8092](http://localhost:8092)
- Usar las credenciales definidas en `.env`.

### 4. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

La API estará disponible en `http://localhost:8083/api/v1/veterinarios` (o la ruta que hayas configurado). Swagger estará en `http://localhost:8083/swagger-ui/index.html`.

## Equipo

- Eduardo Bray
- Rodrigo Callealta
- Fernando Villalobos

## Microservicio Desarrollado Por Rodrigo Callealta

- user github = lironscallealta

> **DuocUC — FullStack 1 © 2026**
