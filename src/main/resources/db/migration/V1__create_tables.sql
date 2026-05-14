-- 1. Tabla Turnos
CREATE TABLE turnos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    horario_turno VARCHAR(12) NOT NULL
);

-- 2. Tabla Horarios
CREATE TABLE horarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dia VARCHAR(255) NOT NULL,
    id_turno BIGINT,
    CONSTRAINT fk_horario_turno FOREIGN KEY (id_turno) REFERENCES turnos(id)
);

-- 3. Tabla Veterinarios
CREATE TABLE veterinarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_profesional VARCHAR(12) NOT NULL UNIQUE,
    annio_egreso DATE,
    cirujano BOOLEAN
);

-- 4. Tabla Intermedia (Veterinarios - Horarios)
CREATE TABLE veterinarios_horarios (
    veterinario_id BIGINT NOT NULL,
    horario_id BIGINT NOT NULL,
    PRIMARY KEY (veterinario_id, horario_id),
    CONSTRAINT fk_vh_veterinario FOREIGN KEY (veterinario_id) REFERENCES veterinarios(id),
    CONSTRAINT fk_vh_horario FOREIGN KEY (horario_id) REFERENCES horarios(id)
);
