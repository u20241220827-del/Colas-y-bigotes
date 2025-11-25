INSERTS PREVIOS PARA CORRECTO FUNCIONAMIENTO

Insert para la tabla rol
Insert into rol (nombre) value ('ROLE_USUARIO')
Insert into rol (nombre) value ('ROLE_ADMIN')
Insert into rol (nombre) value ('ROLE_VETERINARIO')

Insert para volver administrador
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1,2)
delete  FROM proyectofinal.usuario_rol where usuario_id =2 and rol_id=1

Insert para la tabla estado_cita
Insert into estado_cita ( nombre) values ('TERMINADA')
Insert into estado_cita ( nombre) values ('PROGRAMADA')

Delete para dejar al administrador con un solo rol 
DELETE FROM usuario_rol 
WHERE usuario_id=2
AND rol_id=1;
