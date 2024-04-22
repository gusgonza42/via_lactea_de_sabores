DROP DATABASE IF EXISTS AC1_genshin_impact;
CREATE DATABASE IF NOT EXISTS AC1_genshin_impact;
USE AC1_genshin_impact;

CREATE TABLE personajes(
	id INT PRIMARY KEY,
    nombre VARCHAR (255),
    nivel INT,
    ataque_base INT,
    atributo_principal VARCHAR(255)   
);

CREATE TABLE combates (
	id INT PRIMARY KEY,
    id_personaje INT,
    fecha DATE,
    resultado VARCHAR(255),
    CONSTRAINT fk_combates_pj FOREIGN KEY (id_personaje) REFERENCES personajes(id)
);

INSERT INTO personajes (id, nombre, nivel, ataque_base, atributo_principal)
VALUES (1, "Amber", 50, 500, "Fuego"),
		(2, "Barbara", 50, 450, "Agua"),
        (3, "Beidou", 60, 600, "Rayo"),
        (4, "Chongyun", 55, 550, "Hielo");

INSERT INTO combates(id, id_personaje, fecha, resultado)
VALUES (1, 1, "2022-01-01","Ganado"),
		(2, 2, "2022-01-02", "Perdido"),
        (3, 3, "2022-01-03", "Ganado"),
        (4, 4, "2022-01-04", "Perdido");
	
# INDICES
/*
 -- 1 Elimina el Constraint de Primary Key de personajes y 
	crea un índice de Clave Primaria en su lugar.
    Teoría sobre Índices Únicos en SQL:
Un índice único en SQL se utiliza para garantizar que los valores en una columna específica
de una tabla sean únicos, es decir, que no haya duplicados. Esto es útil cuando queremos 
aplicar restricciones de integridad para evitar datos duplicados en una columna que debe 
contener valores únicos, como identificadores únicos.

Planteamiento:
Eliminar la restricción de clave foránea en "combates": Debido a que la tabla "combates" 
tiene una restricción de clave foránea que hace referencia a la columna "id" en la tabla 
"personajes", primero necesitamos eliminar esta restricción antes de poder eliminar la 
clave primaria en "personajes".
Eliminar la restricción de clave primaria en "personajes": Después de eliminar la 
restricción de clave foránea, podemos proceder a eliminar la restricción de clave primaria 
en la tabla "personajes".
Crear un índice único en "personajes": Una vez que hemos eliminado ambas restricciones, 
podemos crear el índice único en la columna "id" de la tabla "personajes".

*/
ALTER TABLE combates DROP FOREIGN KEY fk_combates_pj;
ALTER TABLE personajes DROP PRIMARY KEY;
CREATE UNIQUE INDEX idx_id ON personajes (id);

/*
 -- 2. Crear un índice único para la columna “nombre” de la tabla “personaje” que
no permita nombres duplicados.

Teoría sobre Índices Únicos en SQL:
Los índices únicos en SQL se utilizan para garantizar que los valores en una columna 
específica de una tabla sean únicos, es decir, que no haya duplicados. Esto es útil cuando 
queremos aplicar restricciones de integridad para evitar datos duplicados en una columna que 
debe contener valores únicos, como nombres de usuario, números de identificación, etc.

Planteamiento:
Identificar la columna: Primero, necesitamos identificar la columna en la que queremos 
asegurar la unicidad de los valores. En este caso, queremos que los nombres de los personajes 
en la tabla "personajes" sean únicos.
Crear un índice único: Utilizaremos el comando CREATE UNIQUE INDEX seguido del nombre del
 índice y el nombre de la columna en la que queremos aplicar la restricción de unicidad.
*/
CREATE UNIQUE INDEX idx_nombre ON personajes (nombre);

/*
 -- 3. Crear un índice compuesto por el “id_personaje” y la “fecha” en la tabla
“combates”.

Teoría sobre Índices Compuestos en SQL:
Un índice compuesto en SQL es un índice que se crea en múltiples columnas de una tabla. 
Este tipo de índice es útil cuando las consultas frecuentes implican la combinación de valores
de varias columnas. Al crear un índice compuesto, mejoramos la eficiencia de estas consultas 
al permitir que la base de datos acceda rápidamente a los datos basados en la combinación de
 valores de las columnas indexadas.

Planteamiento:
Identificar las columnas: En este caso, necesitamos identificar las columnas "id_personaje"
 y "fecha" en la tabla "combates" para crear el índice compuesto.
Crear el índice compuesto: Utilizaremos el comando CREATE INDEX seguido del nombre del 
índice y la lista de las columnas que queremos incluir en el índice.
*/

CREATE INDEX idx_id_personaje_fecha ON combates(id_personaje, fecha);

/*
 -- 4. Crear un índice ordinario en la tabla “combates” que permita la rápida
búsqueda por el campo “id_personaje”.

Teoría sobre Índices Ordinarios en SQL:
Un índice ordinario en SQL se utiliza para acelerar la búsqueda de registros en una tabla 
según los valores de una columna específica. A diferencia de los índices únicos, los índices 
ordinarios permiten valores duplicados en la columna indexada. Se utilizan para mejorar el 
rendimiento de las consultas que buscan registros basados en el valor de una columna, 
pero no requieren que los valores sean únicos.

Planteamiento:
Identificar la columna: En este caso, necesitamos identificar la columna "id_personaje" en 
la tabla "combates" para crear el índice ordinario.
Crear un índice ordinario: Utilizaremos el comando CREATE INDEX seguido del nombre del 
índice y el nombre de la columna en la que queremos crear el índice.
*/

CREATE INDEX idx_id_personaje ON combates (id_personaje);

# Triggers

/*
	-- 5. Crear un trigger para registrar en una tabla llamada "historial_combates" todas las
inserciones en la tabla "combates".

Teoría sobre Triggers en SQL:
Un trigger en SQL es un tipo de procedimiento almacenado que se ejecuta automáticamente en 
respuesta a ciertos eventos en una tabla o vista, como inserciones, actualizaciones o 
eliminaciones de filas. Los triggers se utilizan para implementar lógica adicional, 
como la auditoría de datos, la aplicación de reglas de negocio complejas o la actualización 
de otras tablas relacionadas.

Planteamiento:
Identificar el evento: En este caso, el evento que nos interesa es la inserción de filas 
en la tabla "combates".
Definir la acción a realizar: Queremos registrar todas las inserciones en la tabla 
"combates" en una tabla llamada "historial_combates".

*/
-- DELIMITER //
CREATE TRIGGER registrar_combate
AFTER INSERT ON combates
FOR EACH ROW
-- BEGIN
	INSERT INTO historial_combates (id_combate, id_personaje, fecha, resultado)
    VALUES (NEW.id, NEW.id_personaje, NEW.fecha, NEW.resultado);
-- END;
-- //
-- DELIMITER ;

/*
 -- 6. Crear un trigger para actualizar automáticamente el nivel de los personajes después de
cada combate (ganado o perdido). El nivel se incrementa en 1.

Teoría sobre Triggers en SQL:
Un trigger en SQL es un tipo de procedimiento almacenado que se ejecuta automáticamente 
en respuesta a ciertos eventos en una tabla, como inserciones, actualizaciones o 
eliminaciones de filas. Los triggers se utilizan para implementar lógica adicional, 
como la auditoría de datos, la aplicación de reglas de negocio complejas o la actualización 
de otras tablas relacionadas.

Planteamiento:
Identificar el evento: El evento de interés en este caso es la inserción de filas en la 
tabla "combates".
Definir la acción a realizar: Queremos crear un trigger que se active después de 
cada inserción en la tabla "combates" para actualizar automáticamente el nivel de los 
personajes asociados a los combates, incrementándolo en 1.

*/
-- DELIMITER //
CREATE TRIGGER actualizar_nivel_despues_combate
AFTER INSERT ON combates
FOR EACH ROW
-- BEGIN
	UPDATE personajes
    SET nivel = nivel + 1
    WHERE id = NEW.id_personaje;
-- END;
-- //
-- DELIMITER ;

/*
	--  7. Crear un trigger que registre todo lo que se elimina de la tabla "combates" 
    también en la tabla "historial_combates". Para ello añade una nueva columna "accion" 
    a la tabla (con ALTER TABLE) y modifica el trigger anterior (Haz un DROP y vuelve a 
    crearlo) así no modificas el script que tenías hasta ahora. La nueva columna tiene 
    los valores "INSERT" o "DELETE" en función de si se ha eliminado el registro o añadido.
    
    
*/
DELIMITER //
ALTER TABLE historial_combates
ADD COLUMN accion VARCHAR(10);
DROP TRIGGER IF EXISTS registrar_eliminacion_combate;

DROP TRIGGER IF EXISTS actualizar_nivel_despues_combate;

CREATE TRIGGER actualizar_nivel_despues_combate
AFTER INSERT ON combates
FOR EACH ROW
BEGIN
    UPDATE personajes
    SET nivel = nivel + 1
    WHERE id = NEW.id_personaje;

    -- Registrar la acción en el historial de combates
    INSERT INTO historial_combates (id_combate, id_personaje, fecha, resultado, accion)
    VALUES (NEW.id, NEW.id_personaje, NEW.fecha, NEW.resultado, 'INSERT');
END;
DELIMITER ;

CREATE TRIGGER registrar_eliminacion_combate
AFTER DELETE ON combates
FOR EACH ROW
-- BEGIN
    -- Registrar la acción en el historial de combates
    INSERT INTO historial_combates (id_combate, id_personaje, fecha, resultado, accion)
    VALUES (OLD.id, OLD.id_personaje, OLD.fecha, OLD.resultado, 'DELETE');
-- END;

# Vistas
/*
 8. Crear una vista que muestre el poder de ataque de todos los personajes en función de su
nivel y ataque base. Para ello se utiliza la fórmula: "ataque = ataque base * nivel".
La vista debe mostrar también el nombre y atributo principal del personaje.

Teoría sobre Vistas en SQL:
Una vista en SQL es como una tabla virtual que contiene los resultados de una consulta. 
En lugar de almacenar los datos físicamente, la vista ejecuta la consulta cada vez que se 
hace referencia a ella. Esto permite simplificar consultas complejas, ocultar detalles de 
implementación y proporcionar un acceso fácil y seguro a los datos.

Planteamiento:
Identificar los datos necesarios: Para crear la vista, necesitamos los siguientes datos de 
la tabla "personajes": nombre, nivel, ataque base y atributo principal.

Aplicar la fórmula para calcular el poder de ataque: Utilizaremos la fórmula proporcionada: 
"ataque = ataque base * nivel" para calcular el poder de ataque de cada personaje.

Seleccionar los datos necesarios para la vista: Creamos una consulta SQL que selecciona el 
nombre del personaje, calcula el poder de ataque según la fórmula y también incluye el 
atributo principal.
*/

CREATE VIEW poder_ataque_personajes AS
SELECT
	nombre,
    ataque_base * nivel AS ataque, atributo_principal
FROM
	personajes;


/*
-- 9. Crear una vista para mostrar la media de ataque por cada elemento utilizando la vista
anterior (apartado 9). La vista debe mostrar mínimo el nombre del atributo principal y 
la media.

*/

CREATE VIEW media_ataque_por_elemento AS
SELECT 
    atributo_principal,
    AVG(ataque) AS media_ataque
FROM 
    poder_ataque_personajes
GROUP BY 
    atributo_principal;

/*
--  10. Crear una vista para mostrar la información de los personajes con su poder de ataque
(vista del apartado 9) y su eficiencia en combates. La eficiencia se calcula como el porcentaje
de combates ganados sobre el total de combates.
“eficiencia = combates_ganados / combates”

*/
SELECT * FROM poder_ataque_personajes;

SELECT * FROM informacion_personajes_con_eficiencia;


DROP DATABASE IF EXISTS AC1_productos;
CREATE DATABASE IF NOT EXISTS AC1_productos;
USE AC1_productos;

CREATE TABLE productos (
	id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ventas (
	id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    id_producto int NOT NULL,
    cantidad INT NOT NULL,
    fecha DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_producto) REFERENCES productos(id)
);

INSERT INTO productos (nombre, precio, stock)
VALUES ("producto1", 10.99, 10),
       ("producto2", 15.50, 5),
       ("producto3", 20.75, 7),
       ("producto4", 12.25, 8),
       ("producto5", 18.99, 3),
       ("producto6", 22.50, 6),
       ("producto7", 9.75, 12),
       ("producto8", 16.00, 9),
       ("producto9", 14.30, 4),
       ("producto10", 21.99, 7);
       

INSERT INTO ventas (id_producto, cantidad, fecha)
VALUES (1, 2, "2002-01-01"),
       (2, 3, "2002-01-02"),
       (3, 1, "2002-01-03"),
       (1, 4, "2002-01-04"),
       (2, 2, "2002-01-05");

/*
-- 11. Crear una función llamada "productos_vendidos" que reciba id_producto (entero) y
retorne cantidad (entero).
● Dentro de la función que retorne cuántas ventas se han hecho de ese producto
*/

DELIMITER //

CREATE FUNCTION productos_vendidos(prod_id INT) RETURNS INT
BEGIN
    DECLARE cantidad_vendida INT;

    SELECT COUNT(*) INTO cantidad_vendida
    FROM ventas
    WHERE id_producto = prod_id;

    RETURN cantidad_vendida;
END //

DELIMITER ;

/*
 --  12. Crear un procedimiento almacenado llamado "actualizar_precio" que reciba:
id_producto (entero) y descuento (decimal) y retorne: mensaje (cadena).
● Dentro del procedimiento almacenado, utilizar una variable llamada "nuevo_precio" para
almacenar el nuevo precio del producto después de aplicar el porcentaje de descuento.
● Utilizar una estructura de control "IF" para verificar si el stock del producto es mayor a 0. Si
es así, actualizar el precio del producto con “nuevo_precio”. La variable mensaje debe
valer “OK” o “KO” en función de si se ha actualizado el precio o no.
*/
DELIMITER //

CREATE PROCEDURE actualizar_precio(
    IN prod_id INT,
    IN descuento DECIMAL(10,2),
    OUT mensaje VARCHAR(255)
)
BEGIN
    DECLARE nuevo_precio DECIMAL(10,2);
    
    -- Calcular el nuevo precio aplicando el descuento
    SET nuevo_precio = (SELECT precio * (1 - descuento / 100) FROM productos WHERE id = prod_id);
    
    -- Verificar si el stock del producto es mayor a 0
    IF (SELECT stock FROM productos WHERE id = prod_id) > 0 THEN
        -- Actualizar el precio del producto
        UPDATE productos SET precio = nuevo_precio WHERE id = prod_id;
        SET mensaje = 'OK'; -- El precio se ha actualizado correctamente
    ELSE
        SET mensaje = 'KO'; -- El stock del producto es igual o menor a 0, no se puede actualizar el precio
    END IF;
END //

DELIMITER ;

CALL actualizar_precio(1, 10.0, @mensaje);
SELECT @mensaje;

/*
-- DCL (1.5 puntos)
(0.5p) 13. Crear un usuario llamado "app_user" con contraseña "password" y concederle permisos
para ver, modificar y añadir en la tabla "productos".
Revocar el permiso de modificar del usuario "app_user" en la tabla "productos"
*/
CREATE USER 'app_user'@'localhost' IDENTIFIED BY 'password';

GRANT SELECT, INSERT, UPDATE ON AC1_productos.productos TO 'app_user'@'localhost';

REVOKE UPDATE ON AC1_productos.productos FROM 'app_user'@'localhost';

/*
--  14. Crear un usuario llamado "sales_user" con contraseña "sales123" y otorgarle solo
permisos de lectura en la tabla "sales".
Añade al usuario “sales_user” el permiso de lectura de la tabla “productos”.

*/

-- Crear el usuario
CREATE USER 'sales_user'@'localhost' IDENTIFIED BY 'sales123';

-- Otorgar permisos de lectura en la tabla "sales"
GRANT SELECT ON AC1_genshin_impact.sales TO 'sales_user'@'localhost';

-- Otorgar permisos de lectura en la tabla "productos"
GRANT SELECT ON AC1_genshin_impact.productos TO 'sales_user'@'localhost';

/*
-- 
*/
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'root';

GRANT ALL PRIVILEGES ON AC1_productos.* TO 'admin'@'localhost';

SET PASSWORD FOR 'admin'@'localhost' = 'root_root';

