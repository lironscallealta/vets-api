-- 1. Tabla Veterinarios
CREATE TABLE veterinarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pnombre VARCHAR(255) NOT NULL,
    snombre VARCHAR(255),
    appaterno VARCHAR(255),
    apmaterno VARCHAR(255),
    rut VARCHAR(22),
    dv VARCHAR(1),
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono_celular VARCHAR(20),
    fecha_nacimiento_vet DATE NOT NULL,
    registro_profesional VARCHAR(12) NOT NULL UNIQUE,
    egreso_profesional DATE,
    opera BOOLEAN
);

-- 2. Tabla Horarios
CREATE TABLE horarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dia VARCHAR(255) NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    veterinario_id BIGINT,
    CONSTRAINT fk_horario_veterinario FOREIGN KEY (veterinario_id) REFERENCES veterinarios(id)
);

