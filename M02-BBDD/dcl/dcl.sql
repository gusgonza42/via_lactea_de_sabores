/*
M02: Permite la creación de usuarios en la base de datos para generar usuarios administradores (DCL).
*/

-- Crear usuario admin con todos los privilegios
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON vialacteadesabores.* TO 'admin'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- Crear usuario encargado con privilegios de SELECT, INSERT, UPDATE y DELETE
CREATE USER 'encargado'@'localhost' IDENTIFIED BY 'encargado';
GRANT SELECT, INSERT, UPDATE, DELETE ON vialacteadesabores.* TO 'encargado'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- Crear usuario normal con privilegios de SELECT
CREATE USER 'normal'@'localhost' IDENTIFIED BY 'normal';
GRANT SELECT ON vialacteadesabores.* TO 'normal'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;
