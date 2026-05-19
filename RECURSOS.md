# Recursos — Vets-API (Veterinarios)

Guía para **ver y administrar la base de datos** con phpMyAdmin. Los puertos salen de tu `.env`.

## Ver la base de datos (phpMyAdmin)

### 1. Levantar MySQL y phpMyAdmin

Desde la carpeta `vets-api`:

```bash
docker compose up db phpmyadmin -d
```

### 2. Abrir en el navegador

| Recurso | Enlace |
|--------|--------|
| **phpMyAdmin (veterinarios)** | [http://localhost:8192](http://localhost:8192) |

### 3. Iniciar sesión

| Campo | Valor |
|-------|--------|
| Usuario | `user` |
| Contraseña | `password` |
| Base de datos | `vets` |

---

## Conexión directa a MySQL

| Parámetro | Valor |
|-----------|--------|
| Host | `localhost` |
| Puerto | `3392` |
| Base de datos | `vets` |
| Usuario | `user` |
| Contraseña | `password` |

---

## Las 3 bases del proyecto (phpMyAdmin)

| Microservicio | phpMyAdmin | Puerto MySQL | Base de datos |
|---------------|------------|--------------|---------------|
| **Pets** | [http://localhost:8190](http://localhost:8190) | `3390` | `pets` |
| **Users** | [http://localhost:8191](http://localhost:8191) | `3391` | `users` |
| **Vets** | [http://localhost:8192](http://localhost:8192) | `3392` | `vets` |

---

## API y Swagger (solo si Spring está corriendo)

| Recurso | Enlace |
|--------|--------|
| Swagger | [http://localhost:8092/swagger-ui/index.html](http://localhost:8092/swagger-ui/index.html) |
