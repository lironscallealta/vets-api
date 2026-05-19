# Recursos — Vets-API (Veterinarios)

Accesos rápidos para desarrollo local. Los puertos salen de tu archivo `.env` en esta carpeta.

## Este microservicio

| Recurso | URL / valor |
|--------|-------------|
| **API** | [http://localhost:8092](http://localhost:8092) |
| **Swagger UI** | [http://localhost:8092/swagger-ui/index.html](http://localhost:8092/swagger-ui/index.html) |
| **phpMyAdmin** | [http://localhost:8192](http://localhost:8192) |
| **MySQL (host)** | `localhost` |
| **MySQL (puerto)** | `3392` |
| **Base de datos** | `vets` |
| **Usuario** | `user` |

## Levantar la base de datos (obligatorio antes de la API)

Desde la carpeta `vets-api`:

```bash
docker compose up db -d
```

Comprueba que el contenedor `db` está en ejecución (Docker Desktop). La app se conecta a `localhost:3392`.

## Ecosistema completo (los 3 microservicios)

| Microservicio | API | Swagger | phpMyAdmin | MySQL |
|---------------|-----|---------|------------|-------|
| **Users** | [8091](http://localhost:8091) | [8091/swagger-ui](http://localhost:8091/swagger-ui/index.html) | [8191](http://localhost:8191) | `3391` → BD `users` |
| **Pets** | [8090](http://localhost:8090) | [8090/swagger-ui](http://localhost:8090/swagger-ui/index.html) | [8190](http://localhost:8190) | `3390` → BD `pets` |
| **Vets** | [8092](http://localhost:8092) | [8092/swagger-ui](http://localhost:8092/swagger-ui/index.html) | [8192](http://localhost:8192) | `3392` → BD `vets` |

Cada uno usa su propio `docker compose` dentro de su carpeta (`users-api`, `pets-api`, `vets-api`).

## Si falla la conexión (`Connection refused` / `Communications link failure`)

1. **MySQL no está corriendo** — Spring/Flyway no encuentran nada en el puerto configurado.
2. En **vets-api** el puerto es **`3392`** (variable `HOST_DB_PORT` en `.env`), no `3306`.
3. Solución: `docker compose up db -d` en `vets-api` y esperar ~30 s al healthcheck.
4. Verifica que exista el archivo `.env` (puedes copiarlo desde `.env.example` y ajustar puertos).

Credenciales por defecto en `.env`: usuario `user`, contraseña `password`, root `root_password`.
