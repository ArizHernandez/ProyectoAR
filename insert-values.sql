INSERT INTO ROL(NOMBRE, DESCRIPCION, ACTIVO, USUARIO_CREA, USUARIO_MOD, FECHA_CREA, FECHA_MOD)
VALUES ('ADMINISTRADOR',
        'ROL DE ADMINISTRADOR',
        1,
        'ADMIN',
        'ADMIN',
        '2018-01-01 00:00:00',
        '2018-01-01 00:00:00');
INSERT INTO ROL(NOMBRE, DESCRIPCION, ACTIVO, USUARIO_CREA, USUARIO_MOD, FECHA_CREA, FECHA_MOD)
VALUES ('USUARIO',
        'ROL DE USUARIO',
        1,
        'ADMIN',
        'ADMIN',
        '2018-01-01 00:00:00',
        '2018-01-01 00:00:00');
INSERT INTO ROL(NOMBRE, DESCRIPCION, ACTIVO, USUARIO_CREA, USUARIO_MOD, FECHA_CREA, FECHA_MOD)
VALUES ('INVITADO',
        'ROL DE INVITADO',
        1,
        'ADMIN',
        'ADMIN',
        '2018-01-01 00:00:00',
        '2018-01-01 00:00:00');


INSERT INTO proyecto_ar.dbo.USUARIO (nombre, apellido, usuario, password, id_rol, fecha_crea, activo,
                                     usuario_mod, usuario_crea, fecha_mod, codigo)
VALUES ( 'Ariz', 'Hernandez', 'ARZS', '123', 1, '2023-04-21 20:58:52.000', 1, '', '',
        '2023-04-21 20:59:01.000', '123');

INSERT INTO proyecto_ar.dbo.USUARIO (nombre, apellido, usuario, password, id_rol, fecha_crea, activo,
                                     usuario_mod, usuario_crea, fecha_mod, codigo)
VALUES ( 'ar', 'test', 'TESTAR', '123', 1, '2023-04-21 20:58:52.000', 1, 'Ariz', 'Ariz',
        '2023-04-21 20:59:01.000', '123');
