/*
Verificar usuario mediante correo electrónico y contraseña (administrador): nos falta crear el campo de password en la tabla usuario
*/

SELECT * FROM USUARIO WHERE email = 'correo_electronico' AND password = 'password';


/*
Añadir nuevo cliente especificando el número de comensales y asignar una mesa libre que cumple las condiciones (Stored Procedure):
*/

CREATE PROCEDURE AsignarMesaLibre(IN id_usuario INT, IN num_comensales INT)
BEGIN
    DECLARE mesa_id INT;
    
    -- Buscar una mesa libre que cumpla las condiciones
    SELECT num_mesa INTO mesa_id
    FROM MESA
    WHERE disponibilidad = TRUE AND capacidad >= num_comensales
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


CALL AsignarMesaLibre(num_comensales);

/*
Listar todas las mesas con los comensales que hay en cada una de ellas ordenado por ID:

*/

SELECT m.num_mesa, m.disponibilidad, COUNT(cm.id_usuario) AS num_comensales
FROM MESA m
LEFT JOIN CLIENTE_MESA Ocm N m.num_mesa = cm.num_mesa
GROUP BY m.num_mesa
ORDER BY m.num_mesa;


/*
Listar las comandas especificando el turno y ordenando por turno: tendremenos un solo turno, entonces lo haremos por fechar
*/
SELECT * FROM comandas WHERE fecha = 'añadir fecha' ORDER BY fecha;

/*
Listar todos los camareros/as y cocineros/as y las mesas o platos que tienen asignados (Function):
*/

-- Función para listar ls camareros y mesas que tienen asignadas
DELIMITER //
CREATE FUNCTION listarCamarerosMesas() RETURNS TEXT DETERMINISTIC
BEGIN
    DECLARE resultado TEXT;
    SELECT GROUP_CONCAT(CONCAT(u.nombre, ' ', u.apellido1, ' ', u.apellido2, ' - Mesa: ', m.num_mesa) SEPARATOR '\n') INTO resultado
    FROM USUARIO u
    INNER JOIN CAMARERO c ON u.id = c.id_usuario
    INNER JOIN MESA m ON c.id_usuario = m.id_camarero;
    RETURN resultado;
END //
DELIMITER ;

SELECT ListarPersonal();

-- Función para listar chefs y platos que han preparado
DELIMITER //
CREATE FUNCTION listarChefsPlatos() RETURNS TEXT DETERMINISTIC
BEGIN
    DECLARE resultado TEXT;
    SELECT GROUP_CONCAT(CONCAT(u.nombre, ' ', u.apellido1, ' ', u.apellido2, ' - Plato: ', p.nombre_plato) SEPARATOR '\n') 
    INTO resultado
    FROM USUARIO u
    INNER JOIN CHEF c ON USUAuRIO.id = c.id_usuario
    INNER JOIN PLATO p ON c.id_usuario = p.id_chef;
    RETURN resultado;
END //
DELIMITER ;

SELECT listarChefsPlatos();


/*
Listar los menús y los platos que tiene cada menú ordenado por ID:

*/

SELECT m.id_menu, m.nombre_menu, p.id_plato, p.nombre_plato
FROM MENU m
LEFT JOIN MENU_PLATO mp ON m.id_menu = mp.id_menu
LEFT JOIN PLATO p ON mp.id_plato = p.id_plato
ORDER BY m.id_menu;

/*
Añadir o quitar platos a un menú especificando el identificador del plato y del menú añadiendo si no existe la combinación y eliminando si ya existe (Function):
*/

DELIMITER //
CREATE PROCEDURE GestionarPlatosMenu(IN p_id_menu INT, IN p_id_plato INT, IN p_accion VARCHAR(10))
BEGIN
    DECLARE existe BOOLEAN;
    SELECT EXISTS(SELECT * FROM MENU_PLATO WHERE id_menu = p_id_menu AND id_plato = p_id_plato) INTO existe;

    IF p_accion = 'añadir' THEN
        IF existe THEN
            SELECT 'El plato ya existe en el menú' AS mensaje;
        ELSE
            INSERT INTO MENU_PLATO(id_menu, id_plato) VALUES (p_id_menu, p_id_plato);
            SELECT 'Plato añadido correctamente al menú' AS mensaje;
        END IF;
    ELSEIF p_accion = 'quitar' THEN
        IF existe THEN
            DELETE FROM MENU_PLATO WHERE id_menu = p_id_menu AND id_plato = p_id_plato;
            SELECT 'Plato eliminado correctamente del menú' AS mensaje;
        ELSE
            SELECT 'El plato no existe en el menú' AS mensaje;
        END IF;
    ELSE
        SELECT 'Acción no válida' AS mensaje;
    END IF;
END //
DELIMITER ;


CALL GestionarPlatosMenu(1, 2, 'añadir');
CALL GestionarPlatosMenu(1, 2, 'quitar');

/*
Listar platos ordenados por nombre:
*/
SELECT * FROM PLATO ORDER BY nombre_plato;

/*
Mostrar los ingredientes que tiene cada plato ordenados alfabéticamente:
*/

SELECT p.nombre_plato, pr.nombre
FROM PLATO p
INNER JOIN PLATO_PRODUCTO pp ON p.id_plato = pp.id_plato
INNER JOIN PRODUCTO pr ON pp.id_producto = pr.id_producto
ORDER BY p.nombre_plato, pr.nombre;




/*
Mostrar alérgenos de los platos sin repeticiones:

*/
SELECT DISTINCT alergenos FROM MENU_COMANDA;





/*
Listar todos los ingredientes y el stock que tiene:
*/
SELECT p.nombre, p.stock FROM PRODUCTO p;


/*
Crear o modificar un plato especificando sus ingredientes (Stored Procedure):

*/

DELIMITER //
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
END //
DELIMITER ;

CALL CrearOModificarPlato(1, 'Paella', 'Arroz, pollo, mariscos, verduras');
/*
Al añadir ítems a una nueva comanda, verificar que haya stock y que se reste en función de los ingredientes y que no esté cerrada (Stored Procedure):
*/
DELIMITER //
CREATE PROCEDURE VerificarStock(IN p_id_comanda INT, IN p_id_menu INT, OUT mensaje VARCHAR(255))
BEGIN
    DECLARE estaCerrada BOOLEAN;
    DECLARE stockSuficiente BOOLEAN DEFAULT TRUE;
    DECLARE id_producto INT;

    -- Verificar si la comanda está cerrada
    SELECT pagado INTO estaCerrada FROM COMANDA WHERE id_comanda = p_id_comanda;
    IF estaCerrada THEN
        SET mensaje = 'La comanda ya está cerrada';
        LEAVE;
    END IF;

    -- Verificar si hay stock suficiente para todos los platos del menú
    DECLARE cur CURSOR FOR SELECT id_producto FROM MENU_PLATO JOIN PLATO_PRODUCTO ON MENU_PLATO.id_plato = PLATO_PRODUCTO.id_plato WHERE id_menu = p_id_menu;
    OPEN cur;

    FETCH NEXT FROM cur INTO id_producto;
    WHILE stockSuficiente AND id_producto IS NOT NULL DO
        SELECT stock INTO @stockActual FROM PRODUCTO WHERE id_producto = id_producto;
        IF @stockActual <= 0 THEN
            SET stockSuficiente = FALSE;
        END IF;
        FETCH NEXT FROM cur INTO id_producto;
    END WHILE;

    CLOSE cur;

    IF NOT stockSuficiente THEN
        SET mensaje = 'No hay stock suficiente para uno o más productos del menú';
        LEAVE;
    END IF;

    -- Añadir el menú a la comanda y restar el stock de los productos
    INSERT INTO MENU_COMANDA(id_menu, id_comanda) VALUES (p_id_menu, p_id_comanda);

    FETCH FIRST FROM cur INTO id_producto;
    WHILE id_producto IS NOT NULL DO
        UPDATE PRODUCTO SET stock = stock - 1 WHERE id_producto = id_producto;
        FETCH NEXT FROM cur INTO id_producto;
    END WHILE;

    CLOSE cur;

    SET mensaje = 'Ítem añadido a la comanda exitosamente';
END //
DELIMITER ;

CALL VerificarStock(1, 1, @mensaje);
SELECT @mensaje;

/*
Calcular el precio total de una comanda (Function):

*/

DELIMITER //
CREATE FUNCTION CalcularPrecioTotalComanda(p_id_comanda INT) RETURNS DECIMAL(10,2)
BEGIN
    DECLARE precioTotal DECIMAL(10,2);

    SELECT SUM(MENU.precio)
    INTO precioTotal
    FROM MENU_COMANDA
    JOIN MENU ON MENU_COMANDA.id_menu = MENU.id_menu
    WHERE MENU_COMANDA.id_comanda = p_id_comanda;

    RETURN precioTotal;
END //
DELIMITER ;

SELECT CalcularPrecioTotalComanda(1);

/*
Mostrar la facturación que ha hecho una mesa por día con validaciones (Stored Procedure):
*/
DELIMITER //
CREATE PROCEDURE FacturacionMesaPorDia(IN p_id_mesa INT, IN p_fecha DATE, OUT facturacion DECIMAL(10,2))
BEGIN
    -- Validar que la mesa exista
    IF NOT EXISTS(SELECT * FROM MESA WHERE id_mesa = p_id_mesa) THEN
        SET facturacion = -1;
        LEAVE;
    END IF;

    -- Calcular la facturación de la mesa para el día especificado
    SELECT SUM(CalcularPrecioTotalComanda(COMANDA.id_comanda))
    INTO facturacion
    FROM COMANDA
    WHERE COMANDA.id_mesa = p_id_mesa AND DATE(COMANDA.fecha) = p_fecha;
END //
DELIMITER ;

CALL FacturacionMesaPorDia(1, '2022-04-28', @facturacion);
SELECT @facturacion;


/*
Cambiar el estado de una mesa de disponible a no disponible o viceversa:
*/

UPDATE MESA SET disponibilidad = NOT disponibilidad WHERE num_mesa = 'introduce num_mesa';

/*
Asignar, reasignar o desasignar mesa a un trabajador/a:

*/
DELIMITER //
CREATE PROCEDURE AsignarReasignarDesasignarMesa(IN p_id_trabajador INT, IN p_num_mesa INT, IN p_accion VARCHAR(255))
BEGIN
    -- Verificar la acción
    CASE p_accion
        WHEN 'asignar' THEN
            -- Asignar la mesa al trabajador
            UPDATE MESA SET id_camarero = p_id_trabajador WHERE num_mesa = p_num_mesa;
        WHEN 'reasignar' THEN
            -- Reasignar la mesa a otro trabajador
            UPDATE MESA SET id_camarero = p_id_trabajador WHERE num_mesa = p_num_mesa;
        WHEN 'desasignar' THEN
            -- Desasignar la mesa del trabajador
            UPDATE MESA SET id_camarero = NULL WHERE num_mesa = p_num_mesa;
    END CASE;
END //
DELIMITER ;

CALL AsignarReasignarDesasignarMesa(3, 5, 'asignar');
CALL AsignarReasignarDesasignarMesa(4, 5, 'reasignar');
CALL AsignarReasignarDesasignarMesa(5, 5, 'desasignar');



