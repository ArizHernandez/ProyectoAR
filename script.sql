CREATE DATABASE proyecto_ar;
USE proyecto_ar;

CREATE TABLE ROL
(
    id_rol       INT          NOT NULL IDENTITY (1,1),
    nombre       VARCHAR(80)  NOT NULL,
    descripcion  VARCHAR(MAX),
    activo       BIT          NOT NULL,
    usuario_crea VARCHAR(250) NOT NULL,
    usuario_mod  VARCHAR(250) NOT NULL,
    fecha_crea   DATETIME     NOT NULL,
    fecha_mod    DATETIME     NOT NULL,
    PRIMARY KEY (id_rol)
)


CREATE TABLE MODULO
(
    id_modulo       INT          NOT NULL IDENTITY (1,1),
    nombre          VARCHAR(250) NOT NULL,
    descripcion     VARCHAR(MAX) NOT NULL,
    path            VARCHAR(250) NOT NULL,
    nivel           BIT          NOT NULL,
    orden           INT          NOT NULL,
    ID_MODULO_PADRE INT,
    fecha_crea      DATETIME     NOT NULL,
    fecha_mod       DATETIME     NOT NULL,
    usuario_crea    VARCHAR(250) NOT NULL,
    usuario_mod     VARCHAR(250) NOT NULL,
    activo          BIT          NOT NULL,
    PRIMARY KEY (id_modulo),
    FOREIGN KEY (ID_MODULO_PADRE) REFERENCES MODULO (id_modulo),
)

CREATE TABLE USUARIO
(
    id_usuario   INT          NOT NULL IDENTITY (1,1),
    nombre       VARCHAR(250) NOT NULL,
    apellido     VARCHAR(250) NOT NULL,
    usuario      VARCHAR(80)  NOT NULL,
    password     VARCHAR(80)  NOT NULL,
    id_rol       INT          NOT NULL,
    activo       BIT          NOT NULL,
    usuario_mod  DATETIME     NOT NULL,
    usuario_crea DATETIME     NOT NULL,
    fecha_mod    DATETIME     NOT NULL,
    fecha_crea   DATETIME     NOT NULL,
    codigo       VARCHAR(20)  NOT NULL,
    PRIMARY KEY (id_usuario, usuario),
    FOREIGN KEY (id_rol) REFERENCES ROL (id_rol)
)

CREATE TABLE PERMISO
(
    id_permiso   INT          NOT NULL IDENTITY (1,1),
    id_module    INT          NOT NULL,
    id_role      INT          NOT NULL,
    fecha_crea   DATETIME     NOT NULL,
    fecha_mod    DATETIME     NOT NULL,
    usuario_crea VARCHAR(250) NOT NULL,
    usuario_mod  VARCHAR(250) NOT NULL,
    activo       BIT          NOT NULL,
    PRIMARY KEY (id_permiso),
    FOREIGN KEY (id_module) REFERENCES MODULO (id_modulo),
    FOREIGN KEY (id_role) REFERENCES ROL (id_rol),
)

