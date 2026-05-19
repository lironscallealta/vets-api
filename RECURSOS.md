# Recursos — Vets-API (Veterinarios)

Solo enlaces de **este** microservicio. Cada API tiene su propio phpMyAdmin en otro puerto.

## phpMyAdmin — base de datos `vets`

### Levantar servicios

Desde la carpeta `vets-api`:

```bash
docker compose up db phpmyadmin -d
```

### Abrir (solo este enlace)

**[http://localhost:8192](http://localhost:8192)**

### Iniciar sesión

| Campo | Valor |
|-------|--------|
| Usuario | `user` |
| Contraseña | `password` |

### Comprobar que es la BD correcta

En el panel izquierdo de phpMyAdmin debe aparecer la base **`vets`**.
Si ves `pets` o `users`, abriste el puerto equivocado (8190 o 8191).

---

## MySQL directo (DBeaver, Workbench)

| Parámetro | Valor |
|-----------|--------|
| Host | `localhost` |
| Puerto | `3392` |
| Base de datos | `vets` |
| Usuario | `user` |
| Contraseña | `password` |

---

## Otros microservicios (no uses estos enlaces aquí)

| Microservicio | phpMyAdmin | Base de datos |
|---------------|------------|---------------|
| Pets | `http://localhost:8190` | `pets` |
| Users | `http://localhost:8191` | `users` |

Documentación en `pets-api/RECURSOS.md` y `users-api/RECURSOS.md`.

---

## Swagger (solo con Spring en ejecución)

[http://localhost:8092/swagger-ui/index.html](http://localhost:8092/swagger-ui/index.html)
