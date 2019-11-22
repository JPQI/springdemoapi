CREATE TABLE empleado (
  id INTEGER PRIMARY KEY,
  nombre varchar(255),
  departamento varchar(255),
  telefono varchar(255)
);


INSERT INTO empleado (id, nombre, departamento, telefono) values(1, 'Juan Perez', 'Logística', '5742130');
INSERT INTO empleado (id, nombre, departamento, telefono) values(2, 'Pedro Soto', 'RRHH', '5234120');

INSERT INTO usuario (id, username, password) values(1, 'admin', '1234');

INSERT INTO roles (id, name) values(1, 'ROLE_USER');
INSERT INTO roles (id, name) values(2, 'ROLE_ADMIN');
INSERT INTO usuario_roles (user_id, roles_id) values(1, 1);

--agregar identity by column