--Tablespace de datos
CREATE TABLESPACE APP_LABORAL
DATAFILE
  'C:\oraclexe\app\oracle\oradata\XE\APP_LABORAL.dbf'
SIZE 512M
ONLINE;

--Tablespace temporal
CREATE TEMPORARY TABLESPACE APP_LABORAL_TEMP
TEMPFILE
'C:\oraclexe\app\oracle\oradata\XE\APP_LABORAL_TEMP.dbf'
SIZE 128M
AUTOEXTEND ON;

CREATE USER APLIC IDENTIFIED BY root123
       DEFAULT TABLESPACE APP_LABORAL
       TEMPORARY TABLESPACE APP_LABORAL_TEMP;
GRANT RESOURCE, CONNECT TO APLIC;
GRANT CREATE SESSION TO APLIC;
GRANT ALL PRIVILEGES TO APLIC;

-- CREACION DE TABLAS, SP Y FUNCIONES
-- -- TABLAS
-- ---------------------------------
CREATE TABLE APLIC.USUARIO (
  usuario_id VARCHAR2(10) NOT NULL,
  nombre VARCHAR2(50),
  edad number(3),
  telefono VARCHAR2(12),
  email VARCHAR2(100),
  privilegio number(1) NOT NULL,
  CONSTRAINT pkusuario PRIMARY KEY (usuario_id)
);

-- ---------------------------------
-- Creacion de cursor que va a ser devuelto en las funciones
CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;

-- ----------------------------------
-- SP
CREATE OR REPLACE PROCEDURE insertarUsuarios(
  u_usuario_id IN USUARIO.usuario_id%TYPE,
  u_nombre IN USUARIO.nombre%TYPE,
  u_edad IN USUARIO.edad%TYPE,
  u_telefono IN USUARIO.telefono%TYPE,
  u_email IN USUARIO.email%TYPE,
  u_privilegio IN USUARIO.privilegio%TYPE 
)
AS
BEGIN
	INSERT INTO USUARIO VALUES(u_usuario_id,u_nombre,u_edad,u_telefono,u_email,u_privilegio);
END;

-- ----------------------------------------------------
CREATE OR REPLACE PROCEDURE modificarUsuarios (
  m_usuario_id IN USUARIO.usuario_id%TYPE,
  m_nombre IN USUARIO.nombre%TYPE,
  m_edad IN USUARIO.edad%TYPE,
  m_telefono IN USUARIO.telefono%TYPE,
  m_email IN USUARIO.email%TYPE,
  m_privilegio IN USUARIO.privilegio%TYPE 
)
AS
BEGIN
  UPDATE USUARIO SET
    nombre = m_nombre,
    edad = m_edad,
    telefono = m_telefono,
    email = m_email,
    privilegio = m_privilegio
  WHERE usuario_id = m_usuario_id;
END;

-- ----------------------------------------------------
-- Funciones
-- ----------------------------------------------------

-- ----------------------------------------------------
CREATE OR REPLACE PROCEDURE eliminarUsuario(m_usuario_id IN USUARIO.usuario_id%TYPE)
AS
BEGIN
    DELETE FROM USUARIO WHERE usuario_id = m_usuario_id;
END;

-- ----------------------------------------------------

-- *****************************************************************************
-- ---------------------------------
-- -- TABLAS
-- ---------------------------------
CREATE TABLE APLIC.SKILL (
  n_skill NUMBER(9),
  usuario_id VARCHAR(10) NOT NULL,
  nombre VARCHAR(100),
  descripcion VARCHAR(100)
);

CREATE SEQUENCE skill_seq START WITH 1;
-- ----------------------------------
-- SP
CREATE OR REPLACE PROCEDURE insertarSkills(
  usuario_id IN SKILL.usuario_id%TYPE,
  nombre IN SKILL.nombre%TYPE,
  descripcion IN SKILL.descripcion%TYPE
)
AS
BEGIN
    INSERT INTO SKILL VALUES(SKILL_SEQ.NEXTVAL,usuario_id,nombre,descripcion);
END;

-- ----------------------------------------------------
CREATE OR REPLACE PROCEDURE modificarSkills (
  m_skill IN SKILL.n_skill%TYPE,
  m_usuario_id IN SKILL.usuario_id%TYPE,
  m_nombre IN SKILL.nombre%TYPE,
  m_descripcion IN SKILL.descripcion%TYPE
)
AS
BEGIN
  UPDATE SKILL SET
    nombre = m_nombre,
    descripcion = m_descripcion
  WHERE usuario_id = m_usuario_id AND N_SKILL = m_skill;
END;
-- ----------------------------------------------------
-- Funciones
-- ----------------------------------------------------
create or replace FUNCTION listarSkills(
    s_usuario_id IN SKILL.usuario_id%TYPE
)
RETURN Types.ref_cursor
AS
    skill_cursor types.ref_cursor;
BEGIN
  OPEN skill_cursor FOR
        SELECT n_skill,usuario_id,nombre,descripcion FROM SKILL where usuario_id = s_usuario_id;
  RETURN skill_cursor;
END;
-- ----------------------------------------------------
 ----------------------------------------------------
create or replace PROCEDURE eliminarSkill( 
m_skill IN SKILL.n_skill%TYPE
)
AS
BEGIN
    DELETE FROM SKILL WHERE N_SKILL = m_skill;
END;
-- ----------------------------------------------------

-- *****************************************************************************
-- ---------------------------------
-- -- TABLAS
-- ---------------------------------
CREATE TABLE APLIC.Educacion(
  n_educ NUMBER(9),
  usuario_id VARCHAR(10) NOT NULL,
  institucion VARCHAR(50),
  carrera VARCHAR(40),
  titulo VARCHAR(20),
  anno VARCHAR(4)
);

CREATE SEQUENCE educ_seq START WITH 1;
-- ----------------------------------
-- SP
CREATE OR REPLACE PROCEDURE insertarEducacion(
  e_usuario_id IN Educacion.usuario_id%TYPE,
  e_institucion IN Educacion.institucion%TYPE,
  e_carrera IN Educacion.carrera%TYPE,
  e_titulo IN Educacion.titulo%TYPE,
  e_anno IN Educacion.anno%TYPE
)
AS
BEGIN
    INSERT INTO Educacion VALUES(EDUC_SEQ.NEXTVAL,e_usuario_id,e_institucion,e_carrera,e_titulo,e_anno);
END;

-- ----------------------------------------------------
CREATE OR REPLACE PROCEDURE modificarEducacion (
  m_educ IN Educacion.n_educ%TYPE,
  m_usuario_id IN Educacion.usuario_id%TYPE,
  m_institucion IN Educacion.institucion%TYPE,
  m_carrera IN Educacion.carrera%TYPE,
  m_titulo IN Educacion.titulo%TYPE,
  m_anno IN Educacion.anno%TYPE
)
AS
BEGIN
  UPDATE Educacion SET
    institucion = m_institucion,
    carrera = m_carrera,
	titulo = m_titulo,
	anno = m_anno
  WHERE usuario_id = m_usuario_id AND n_educ = m_educ;
END;
-- ----------------------------------------------------
-- Funciones
-- ----------------------------------------------------
create or replace FUNCTION listarEducacion(
    e_usuario_id IN educacion.usuario_id%TYPE
)
RETURN Types.ref_cursor
AS
    ed_cursor types.ref_cursor;
BEGIN
  OPEN ed_cursor FOR
       SELECT n_educ,usuario_id,institucion,carrera,titulo,anno FROM Educacion where USUARIO_ID = e_usuario_id;
  RETURN ed_cursor;
END;
-- ----------------------------------------------------
 ----------------------------------------------------
CREATE OR REPLACE PROCEDURE eliminarEducacion(
    m_educ IN Educacion.n_educ%TYPE)
AS
BEGIN
    DELETE FROM Educacion WHERE usuario_id = m_usuario_id AND n_educ = m_educ;
END;


CREATE TABLE APLIC.Trabajo(
  N_TRAB NUMBER(9),
  usuario_id VARCHAR(10) NOT NULL,
  empresa VARCHAR(50),
  puesto VARCHAR(40),
  descripcion VARCHAR(200),
  anno_inicio NUMBER(4),
  anno_final NUMBER(4)
);

CREATE SEQUENCE trab_seq START WITH 1;
-- ----------------------------------
-- SP
CREATE OR REPLACE PROCEDURE insertarTrabajo(
  t_usuario_id IN Trabajo.usuario_id%TYPE,
  t_empresa IN Trabajo.empresa%TYPE,
  t_puesto IN Trabajo.puesto%TYPE,
  t_descripcion IN Trabajo.descripcion%TYPE,
  t_anno_inicio IN Trabajo.anno_inicio%TYPE,
  t_anno_final IN Trabajo.anno_final%TYPE
)
AS
BEGIN
    INSERT INTO Trabajo VALUES(TRAB_SEQ.NEXTVAL,t_usuario_id,t_empresa,t_puesto,t_descripcion,t_anno_inicio,t_anno_final);
END;

-- ----------------------------------------------------
CREATE OR REPLACE PROCEDURE modificarTrabajo (
  m_trab IN TRABAJO.N_TRAB%TYPE,
  m_usuario_id IN Trabajo.usuario_id%TYPE,
  m_empresa IN Trabajo.empresa%TYPE,
  m_puesto IN Trabajo.puesto%TYPE,
  m_descripcion IN Trabajo.descripcion%TYPE,
  m_anno_inicio IN Trabajo.anno_inicio%TYPE,
  m_anno_final IN Trabajo.anno_final%TYPE
)
AS
BEGIN
  UPDATE Trabajo SET
    empresa = m_empresa,
	puesto = m_puesto,
	descripcion = m_descripcion,
	anno_inicio = m_anno_inicio,
	anno_final =m_anno_final
  WHERE usuario_id = m_usuario_id AND N_TRAB = m_trab;
END;
-- ----------------------------------------------------
-- Funciones
-- ----------------------------------------------------
create or replace FUNCTION listarTrabajo(
    t_usuario_id IN trabajo.usuario_id%TYPE
)
RETURN Types.ref_cursor
AS
    tr_cursor types.ref_cursor;
BEGIN
    OPEN tr_cursor FOR
        SELECT N_TRAB,USUARIO_ID,empresa,puesto,descripcion,anno_inicio,anno_final FROM trabajo where USUARIO_ID = t_usuario_id;
    RETURN tr_cursor;
END;
-- ----------------------------------------------------
 ----------------------------------------------------
CREATE OR REPLACE PROCEDURE eliminarTrabajo( 
    m_trab IN TRABAJO.N_TRAB%TYPE
)
AS
BEGIN
    DELETE FROM Trabajo WHERE N_TRAB = m_trab;
END;
------------------------------------------------------------------------------------------------------------------------------------


CREATE TABLE APLIC.Empresa(
  empresa_id VARCHAR(10) NOT NULL,
  locacion VARCHAR(50),
  nombre VARCHAR(50),
  privilegio VARCHAR(30), NOT NULL
);
-- ----------------------------------
-- SP
CREATE OR REPLACE PROCEDURE insertarEmpresa(
  empresa_id IN Empresa.empresa_id%TYPE,
  nombre IN Empresa.nombre%TYPE,
  locacion IN Empresa.locacion%TYPE,
  privilegio IN Empresa.privilegio%TYPE
)
AS
BEGIN
    INSERT INTO Empresa VALUES(empresa_id,nombre,locacion);
END;

-- ----------------------------------------------------
CREATE OR REPLACE PROCEDURE modificarEmpresa (
  m_empresa_id IN Empresa.empresa_id%TYPE,
  m_nombre IN Empresa.nombre%TYPE,
  m_locacion IN Empresa.locacion%TYPE
)
AS
BEGIN
  UPDATE Empresa SET
    nombre = m_nombre,
    locacion = m_locacion
  WHERE empresa_id = m_empresa_id;
END;


-- ----------------------------------------------------
-- Funciones
-- ----------------------------------------------------
create or replace FUNCTION listarEmpresa
RETURN Types.ref_cursor
AS
    em_cursor types.ref_cursor;
BEGIN
  OPEN em_cursor FOR
       SELECT empresa_id,nombre, locacion FROM Empresa;
  RETURN em_cursor;
END;
-- ----------------------------------------------------
 ----------------------------------------------------
CREATE OR REPLACE PROCEDURE eliminarEmpresa( m_empresa_id IN Empresa.empresa_id%TYPE)
AS
BEGIN
    DELETE FROM Empresa WHERE empresa_id = m_empresa_id;
END;


CREATE TABLE APLIC.Puesto(
  puesto_id VARCHAR(10) NOT NULL,
  empresa_id VARCHAR(10) NOT NULL,
  area VARCHAR(30),
  nombre VARCHAR(30),
  descripcion VARCHAR(200),
  requisitos VARCHAR(200),
  horario VARCHAR(20),
  vigente NUMBER(1)
);

-- ----------------------------------
-- SP
CREATE OR REPLACE PROCEDURE insertarPuesto(
  puesto_id IN Puesto.puesto_id%TYPE,
  empresa_id IN Puesto.empresa_id%TYPE,
  area IN Puesto.area%TYPE,
  nombre IN Puesto.nombre%TYPE,
  descripcion IN Puesto.descripcion%TYPE,
  requisitos IN Puesto.requisitos%TYPE,
  horario IN Puesto.horario%TYPE,
  vigente IN Puesto.vigente%TYPE
)
AS
BEGIN
    INSERT INTO Puesto VALUES(puesto_id,empresa_id,area,nombre,descripcion,requisitos,horario,vigente);
END;

-- ----------------------------------------------------
CREATE OR REPLACE PROCEDURE modificarPuesto (
  m_puesto_id IN Puesto.puesto_id%TYPE,
  m_empresa_id IN Puesto.empresa_id%TYPE,
  m_area IN Puesto.area%TYPE,
  m_nombre IN Puesto.nombre%TYPE,
  m_descripcion IN Puesto.descripcion%TYPE,
  m_requisitos IN Puesto.requisitos%TYPE,
  m_horario IN Puesto.horario%TYPE,
  m_vigente IN Puesto.vigente%TYPE
)
AS
BEGIN
  UPDATE Puesto SET
    nombre = m_nombre,
	empresa_id = m_empresa_id,
	area = m_area,
	descripcion = m_descripcion,
	requisitos = m_requisitos,
	horario = m_horario,
	vigente =m_vigente
	
  WHERE puesto_id = m_puesto_id;
END;
-- ----------------------------------------------------
-- Funciones
-- ----------------------------------------------------
create or replace FUNCTION listarPuesto
RETURN Types.ref_cursor
AS
    ps_cursor types.ref_cursor;
BEGIN
  OPEN ps_cursor FOR
       SELECT p.puesto_id,p.area,p.descripcion,p.requisitos,p.horario, e.nombre, e.locacion FROM Puesto p 
	   INNER JOIN empresa e ON p.empresa_id = e.empresa_id;
  RETURN ps_cursor;
END;
-- ----------------------------------------------------
 ----------------------------------------------------
CREATE OR REPLACE PROCEDURE eliminarPuesto( m_puesto_id IN Puesto.puesto_id%TYPE)
AS
BEGIN
    DELETE FROM Puesto WHERE puesto_id = m_puesto_id;
END;

-- -------------------------------------------------
CREATE TABLE APLIC.REFERENCIAS (
  n_ref number(9) NOT NULL PRIMARY KEY,
  usuario_id VARCHAR2(12) NOT NULL,
  nombre VARCHAR2(50),
  telefono VARCHAR2(12),
  email VARCHAR2(100),
  descripcion varchar2(200)
);

CREATE SEQUENCE ref_seq START WITH 1;

CREATE OR REPLACE PROCEDURE insertarReferencia(
  u_usuario_id IN REFERENCIAS.usuario_id%TYPE,
  u_nombre IN REFERENCIAS.nombre%TYPE,
  u_telefono IN REFERENCIAS.telefono%TYPE,
  u_email IN REFERENCIAS.email%TYPE,
  u_descripcion IN REFERENCIAS.descripcion%TYPE 
)
AS
BEGIN
	INSERT INTO REFERENCIAS VALUES(REF_SEQ.NEXTVAL,u_usuario_id,u_nombre,u_telefono,u_email,u_descripcion);
END;

CREATE OR REPLACE PROCEDURE modificarReferencias (
  m_ref IN REFERENCIAS.n_ref%TYPE,
  m_usuario_id IN REFERENCIAS.usuario_id%TYPE,
  m_nombre IN REFERENCIAS.nombre%TYPE,
  m_telefono IN REFERENCIAS.telefono%TYPE,
  m_email IN REFERENCIAS.email%TYPE,
  m_descripcion IN REFERENCIAS.descripcion%TYPE 
)
AS
BEGIN
  UPDATE REFERENCIAS SET
    nombre = m_nombre,
    telefono = m_telefono,
    email = m_email,
    descripcion = m_descripcion
  WHERE usuario_id = m_usuario_id and n_ref = m_ref;
END;

create or replace FUNCTION listarReferencias(
    s_usuario_id IN referencias.usuario_id%TYPE
)
RETURN Types.ref_cursor
AS
    refe_cursor types.ref_cursor;
BEGIN
  OPEN refe_cursor FOR
        SELECT n_ref,usuario_id,nombre,telefono,email,descripcion FROM referencias where usuario_id = s_usuario_id;
  RETURN refe_cursor;
END;

create or replace PROCEDURE eliminarReferencia( 
    m_ref IN REFERENCIAS.N_REF%TYPE
)
AS
BEGIN
    DELETE FROM referencias WHERE N_REF = m_ref;
END;

-- --------------------------------------------------
CREATE TABLE PERFIL(
    ID VARCHAR2(12) NOT NULL PRIMARY KEY,
    NOMBRE VARCHAR2(50),
    LUGAR_HABITACION VARCHAR2(50),
    EDAD NUMBER(3),
    CORREO VARCHAR2(30),
    TELEFONO VARCHAR2(12),
    DESCRIPCION VARCHAR2(200)
);

create or replace FUNCTION LISTARPERFIL(
    e_usuario_id IN educacion.usuario_id%TYPE
)
RETURN Types.ref_cursor
AS
    pe_cursor types.ref_cursor;
BEGIN
  OPEN pe_cursor FOR
       SELECT id,nombre,lugar_habitacion,edad,correo,telefono FROM perfil where id = e_usuario_id;
  RETURN pe_cursor;
END;

create or replace PROCEDURE modificarPerfil (
  m_id IN PERFIL.ID%TYPE,
  m_nombre IN PERFIL.NOMBRE%TYPE,
  m_lugar IN PERFIL.LUGAR_HABITACION%TYPE,
  m_edad IN PERFIL.EDAD%TYPE,
  m_correo IN PERFIL.CORREO%TYPE,
  m_telefono IN PERFIL.TELEFONO%TYPE,
  m_descripcion IN PERFIL.DESCRIPCION%TYPE
)
AS
BEGIN
  UPDATE perfil SET
    nombre = m_nombre,
    lugar_habitacion = m_lugar,
	edad = m_edad,
    correo = m_correo,
    telefono = m_telefono,
    descripcion = m_descripcion
  WHERE id = m_id;
END;

create or replace PROCEDURE INSERTARPERFIL(
  m_id IN PERFIL.ID%TYPE,
  m_nombre IN PERFIL.NOMBRE%TYPE,
  m_lugar IN PERFIL.LUGAR_HABITACION%TYPE,
  m_edad IN PERFIL.EDAD%TYPE,
  m_correo IN PERFIL.CORREO%TYPE,
  m_telefono IN PERFIL.TELEFONO%TYPE,
  m_descripcion IN PERFIL.DESCRIPCION%TYPE
)
AS
BEGIN
    INSERT INTO PERFIL VALUES(m_id,m_nombre,m_lugar,m_edad,m_correo,m_telefono,m_descripcion);
END;

-- --------------------------------------------------------------------------------------------