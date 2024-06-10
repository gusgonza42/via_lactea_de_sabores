DROP DATABASE IF EXISTS vialacteadesabores;

CREATE DATABASE vialacteadesabores;

USE vialacteadesabores;

-- Crear la tabla de usuarios
CREATE TABLE USUARIO (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellido1 VARCHAR(255) NOT NULL,
    apellido2 VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE,
    email VARCHAR(255) UNIQUE NOT NULL,
    fecha_registro DATE ,
    telefono VARCHAR(9) CHECK ( telefono REGEXP '^[0-9]{9}$'),
    contrasena VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

-- Crear tabla chef
CREATE TABLE CHEF(
    id_usuario INT,
    salario DECIMAL(10,2),
    fecha_contratacion DATE NOT NULL,
    disponible BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY(id_usuario) REFERENCES USUARIO(id)
);

-- Crear la tabla de camareros
CREATE TABLE CAMARERO (
    id_usuario INT,
    salario DECIMAL(8,2) CHECK (salario > 0),
    fecha_contratacion DATE NOT NULL,
    disponibilidad BOOLEAN,
    PRIMARY KEY(id_usuario),
    FOREIGN KEY(id_usuario) REFERENCES USUARIO(id)
);



-- Crear la tabla de mesas
CREATE TABLE MESA (
    num_mesa INT CHECK (num_mesa > 0),
    id_camarero INT,
    fecha_registro DATE NOT NULL,
    disponibilidad BOOLEAN DEFAULT TRUE,
    max_comensales INT CHECK (max_comensales > 0),
    PRIMARY KEY(num_mesa),
    FOREIGN KEY (id_camarero) REFERENCES CAMARERO(id_usuario)
);

-- Crear tabla clientes
CREATE TABLE CLIENTE (
    id_usuario INT,
    comensales INT,
    fecha_ultimaReserva DATE,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id)
);

-- Crear tabla intermedia clientes_mesa
CREATE TABLE CLIENTE_MESA(
    id_usuario INT,
    num_mesa INT,
    fecha_reserva DATE NOT NULL,
    PRIMARY KEY (id_usuario,num_mesa),
    FOREIGN KEY (id_usuario) REFERENCES CLIENTE(id_usuario),
    FOREIGN KEY (num_mesa) REFERENCES MESA(num_mesa)
);
-- Crear la tabla de comandas
CREATE TABLE COMANDA (
    id_comanda INT AUTO_INCREMENT,
    fecha DATE NOT NULL,
    pagado BOOLEAN DEFAULT FALSE,
    num_mesa INT,
    PRIMARY KEY(id_comanda),
    FOREIGN KEY (num_mesa) REFERENCES MESA(num_mesa)
);

-- Crear la tabla de menus
CREATE TABLE MENU (
    id_menu INT AUTO_INCREMENT,
    nombre_menu VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    precio DECIMAL(10,2) CHECK (precio > 0),
    PRIMARY KEY(id_menu)
);

-- Crear tabla intermedia entre menu_comanda
CREATE TABLE MENU_COMANDA(
    id_menu INT,
    id_comanda INT,
    alergenos VARCHAR(255),
    PRIMARY KEY (id_menu,id_comanda),
    FOREIGN KEY (id_menu) REFERENCES MENU(id_menu),
    FOREIGN KEY (id_comanda) REFERENCES COMANDA(id_comanda)
);

-- Crear la tabla de platos
CREATE TABLE PLATO (
    id_plato INT AUTO_INCREMENT,
    nombre_plato VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    id_chef int,
    PRIMARY KEY(id_plato),
    FOREIGN KEY (id_chef) REFERENCES CHEF(id_usuario)
);

-- CREAR TABLA INTERMEDIA PLATO_MENU
CREATE TABLE MENU_PLATO(
    id_menu INT,
    id_plato INT,
    PRIMARY KEY (id_menu,id_plato),
    FOREIGN KEY (id_menu) REFERENCES MENU(id_menu),
    FOREIGN KEY (id_plato) REFERENCES PLATO(id_plato)
);


-- Crear la tabla de productos
CREATE TABLE PRODUCTO (
    id_producto INT AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    stock INT CHECK (stock >= 0),
    proveedor ENUM('Rungis Market', 'Miyazaki Wagyu', 'Tsar Nicoulai Caviar') NOT NULL,
    PRIMARY KEY(id_producto)
);

-- Crear la tabla intermedia plato_producto
CREATE TABLE PLATO_PRODUCTO (
    id_plato INT,
    id_producto INT,
    PRIMARY KEY(id_plato, id_producto),
    FOREIGN KEY(id_plato) REFERENCES PLATO(id_plato),
    FOREIGN KEY(id_producto) REFERENCES PRODUCTO(id_producto)
);

/* - - - - - - - - - - - - PROCEDURES - - - - - - - - - - - - - */


-- 1P. PROCEDURE PARA ASIGNAR UNA MESA LIBRE A UN CLIENTE

CREATE PROCEDURE AsignarMesaLibre(IN id_usuario INT, IN num_comensales INT)
BEGIN
    DECLARE mesa_id INT;

    -- Buscar una mesa libre que cumpla las condiciones
    SELECT num_mesa INTO mesa_id
    FROM MESA
    WHERE disponibilidad = TRUE AND max_comensales >= num_comensales
    LIMIT 1;

    -- Asignar la mesa al nuevo cliente
    INSERT INTO CLIENTE_MESA (id_usuario, num_mesa, fecha_reserva)
    VALUES (id_usuario, mesa_id, CURDATE());

    -- Actualizar la disponibilidad de la mesa
    UPDATE MESA
    SET disponibilidad = FALSE
    WHERE num_mesa = mesa_id;

    -- Devolver el número de mesa asignado
    SELECT mesa_id AS num_mesa_asignada;
END;

-- CALL AsignarMesaLibre(id_usuario, num_comensales);

-- 2P. Crear o modificar un plato especificando sus ingredientes
/*
CREATE PROCEDURE CrearOModificarPlato(IN p_id_plato INT, IN p_nombre_plato VARCHAR(255), IN p_descripcion TEXT, IN p_id_chef INT, IN p_id_producto INT)
BEGIN
    DECLARE existe BOOLEAN;
    SELECT EXISTS(SELECT * FROM PLATO WHERE id_plato = p_id_plato) INTO existe;

    IF existe THEN
        UPDATE PLATO SET nombre_plato = p_nombre_plato, descripcion = p_descripcion, id_chef = p_id_chef WHERE id_plato = p_id_plato;
        IF NOT EXISTS(SELECT * FROM PLATO_PRODUCTO WHERE id_plato = p_id_plato AND id_producto = p_id_producto) THEN
            INSERT INTO PLATO_PRODUCTO(id_plato, id_producto) VALUES (p_id_plato, p_id_producto);
        END IF;
    ELSE
        INSERT INTO PLATO(id_plato, nombre_plato, descripcion, id_chef) VALUES (p_id_plato, p_nombre_plato, p_descripcion, p_id_chef);
        INSERT INTO PLATO_PRODUCTO(id_plato, id_producto) VALUES (p_id_plato, p_id_producto);
    END IF;
END;

-- CALL CrearOModificarPlato(1, 'Paella', 'Arroz, pollo, mariscos, verduras');
*/
-- 3P. agregar items a una comanda

