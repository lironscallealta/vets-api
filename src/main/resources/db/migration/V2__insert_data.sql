-- Insertar Turnos iniciales
INSERT INTO turnos (horario_turno, hora_inicio, hora_fin) VALUES
('MAÑANA', '08:00:00', '14:00:00'), -- 1
('TARDE', '14:00:00', '20:00:00'), -- 2
('NOCHE', '20:00:00', '02:00:00'), -- 3
('MADRUGADA', '02:00:00', '08:00:00'); -- 4

-- Insertar Horarios de ejemplo
-- Asumiremos que el turno 1 es MAÑANA, 2 es TARDE, 3 es NOCHE, 4 es MADRUGADA
INSERT INTO horarios (dia, id_turno) VALUES
('Lunes', 1), -- 1
('Lunes', 2), -- 2
('Martes', 1), -- 3
('Martes', 2), -- 4
('Miércoles', 1), -- 5
('Miércoles', 2), -- 6
('Jueves', 1), -- 7
('Jueves', 2), -- 8
('Viernes', 1), -- 9
('Viernes', 2), -- 10
('Viernes', 3), -- 11
('Viernes', 4), -- 12
('Sábado', 1), -- 13
('Domingo', 4); -- 14

-- Insertar Veterinarios de ejemplo
INSERT INTO veterinarios (numero_profesional, annio_egreso, cirujano) VALUES
('VET-12345678', '2015-12-10', TRUE),
('VET-87654321', '2020-01-15', FALSE),
('VET-11223344', '2010-05-20', FALSE);

-- Insertar la relación Veterinarios - Horarios
INSERT INTO veterinarios_horarios (veterinario_id, horario_id) VALUES
-- Vet 2: Lunes y Martes
(2, 1), -- Vet 2 trabaja Lunes mañana
(2, 2), -- Vet 2 trabaja Lunes tarde
(2, 3), -- Vet 2 trabaja Martes mañana
(2, 4), -- Vet 2 trabaja Martes tarde

-- Vet 3: Miércoles y Jueves
(3, 5), -- Vet 3 trabaja Miércoles mañana
(3, 6), -- Vet 3 trabaja Miércoles tarde
(3, 7), -- Vet 3 trabaja Jueves mañana
(3, 8), -- Vet 3 trabaja Jueves tarde

-- Vet 1 (Cirujano): Viernes completo, Sábado mañana, Domingo madrugada
(1, 9),  -- Vet 1 trabaja Viernes mañana
(1, 10), -- Vet 1 trabaja Viernes tarde
(1, 11), -- Vet 1 trabaja Viernes noche
(1, 12), -- Vet 1 trabaja Viernes madrugada
(1, 13), -- Vet 1 trabaja Sábado mañana
(1, 14), -- Vet 1 trabaja Domingo madrugada

-- NUEVOS: Trabajando juntos el Viernes y Domingo
(2, 9),  -- Vet 2 Acompaña al Vet 1 el Viernes mañana
(2, 10), -- Vet 2 Acompaña al Vet 1 el Viernes tarde
(3, 11), -- Vet 3 Acompaña al Vet 1 el Viernes noche
(3, 14); -- Vet 3 Acompaña al Vet 1 el Domingo madrugada
