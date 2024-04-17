DROP DATABASE IF EXISTS vialacteadesabores;

CREATE DATABASE vialacteadesabores;

USE vialacteadesabores;

-- Crear la tabla de usuarios
CREATE TABLE USUARIOS (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellido1 VARCHAR(255) NOT NULL,
    apellido2 VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    fecha_registro DATE NOT NULL,
    telefono VARCHAR(9) CHECK ( telefono REGEXP '^[0-9]{8}$'),
    PRIMARY KEY(id)
);

-- Crear tabla chef
CREATE TABLE CHEF(
    id_usuario INT,
    salario DECIMAL(10,2),
    fecha_contratacion DATE NOT NULL,
    disponible BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY(id_usuario) REFERENCES USUARIOS(id)
);

-- Crear la tabla de camareros
CREATE TABLE CAMAREROS (
    id_usuario INT,
    salario DECIMAL(8,2) CHECK (salario > 0),
    fecha_contratacion DATE NOT NULL,
    disponibilidad BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(id_usuario),
    FOREIGN KEY(id_usuario) REFERENCES USUARIOS(id)
);



-- Crear la tabla de mesas
CREATE TABLE MESA (
    num_mesa INT CHECK (num_mesa > 0),
    id_camarero INT,
    fecha_registro DATE NOT NULL,
    PRIMARY KEY(num_mesa),
    FOREIGN KEY (id_camarero) REFERENCES CAMAREROS(id_usuario)
);

-- Crear tabla clientes
CREATE TABLE CLIENTES (
    id_usuario INT,
    comensales INT,
    fecha_reserva DATE NOT NULL,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_usuario) REFERENCES USUARIOS(id)
);

-- Crear tabla intermedia clientes_mesa
CREATE TABLE CLIENTES_MESA(
    id_usuario INT,
    num_mesa INT,
    fecha_reserva DATE NOT NULL,
    PRIMARY KEY (id_usuario,num_mesa),
    FOREIGN KEY (id_usuario) REFERENCES CLIENTES(id_usuario),
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