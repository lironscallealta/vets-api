-- insertar los 5 veterinarios principales
INSERT INTO veterinarios (pnombre, snombre, appaterno, apmaterno, rut, dv, email, telefono_celular, fecha_nacimiento_vet, registro_profesional, egreso_profesional, opera) VALUES
('Eduardo', 'Andrés', 'Bray', 'Pérez', '11222333', '4', 'eduardo.bray@mail.com', '+56911111111', '1985-06-15', 'REG-1001', '2010-12-10', TRUE),         -- ID 1
('Rodrigo', 'Alejandro', 'Callealta', 'Gómez', '44555666', '7', 'rodrigo.callealta@mail.com', '+56922222222', '1990-03-22', 'REG-1002', '2015-01-15', FALSE), -- ID 2
('Fernando', 'Ignacio', 'Villalobos', 'Soto', '77888999', 'K', 'fernando.villalobos@mail.com', '+56933333333', '1988-11-05', 'REG-1003', '2012-05-20', FALSE), -- ID 3
('Camila', 'Fernanda', 'Pinto', 'Rojas', '15555666', '2', 'camila.pinto@mail.com', '+56944444444', '1992-07-14', 'REG-1004', '2017-03-10', TRUE),            -- ID 4
('Javier', 'Andrés', 'Muñoz', 'Silva', '18888999', '5', 'javier.munoz@mail.com', '+56955555555', '1994-09-30', 'REG-1005', '2019-11-18', FALSE);           -- ID 5


-- asignar horarios ya existentes (creados en V2) usando update
-- esto aplica para eduardo (id 1) y rodrigo (id 2)

-- horarios de lunes y miércoles para rodrigo
UPDATE horarios SET veterinario_id = 2 WHERE id IN (1, 2, 3, 4);

-- horarios de viernes, sábado y domingo para eduardo
UPDATE horarios SET veterinario_id = 1 WHERE id IN (5, 6, 7, 8);


-- crear e insertar nuevos horarios asignados directamente
-- esto aplica para fernando (id 3), camila (id 4) y javier (id 5)

-- horarios de fernando (martes y jueves mañana)
INSERT INTO horarios (dia, hora_inicio, hora_fin, veterinario_id) VALUES
('MARTES', '08:00:00', '13:00:00', 3),
('JUEVES', '08:00:00', '13:00:00', 3);

-- horarios de camila (martes y jueves tarde)
INSERT INTO horarios (dia, hora_inicio, hora_fin, veterinario_id) VALUES
('MARTES', '14:00:00', '19:00:00', 4),
('JUEVES', '14:00:00', '19:00:00', 4);

-- horarios de javier (sábado y domingo mañana)
INSERT INTO horarios (dia, hora_inicio, hora_fin, veterinario_id) VALUES
('SABADO', '09:00:00', '14:00:00', 5),
('DOMINGO', '10:00:00', '14:00:00', 5);
