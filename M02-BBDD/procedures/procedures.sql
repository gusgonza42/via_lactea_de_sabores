/*
Añadir nuevo cliente especificando el número de comensales y asignar una mesa libre que cumple las condiciones (Stored Procedure):
*/

-- PROCEDURE PARA ASIGNAR UNA MESA LIBRE A UN CLIENTE
DELIMITER //

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
END //

DELIMITER ;


CALL AsignarMesaLibre(id_usuario, num_comensales);

-- sin revisar


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


-- Añadir items a una comanda y verificar el stock de los productos y que se resten en funcion de los ingredientes y que no este cerrada.

DELIMITER //

CREATE PROCEDURE AgregarItemsComanda(
    IN p_id_comanda INT,
    IN p_id_menu INT,
    OUT mensaje VARCHAR(255)
)
BEGIN
    DECLARE estaCerrada BOOLEAN;
    DECLARE stockSuficiente BOOLEAN DEFAULT TRUE;
    DECLARE id_producto INT;
    DECLARE cantidadIngredientes INT;

    -- Verificar si la comanda está cerrada
    SELECT pagado INTO estaCerrada FROM COMANDA WHERE id_comanda = p_id_comanda;
    IF estaCerrada THEN
        SET mensaje = 'La comanda ya está cerrada';
        LEAVE;
    END IF;

    -- Obtener los productos e ingredientes del menú y sus cantidades
    DECLARE curMenu CURSOR FOR
        SELECT PP.id_producto, COUNT(*) as cantidad
        FROM MENU_PLATO MP
        INNER JOIN PLATO_PRODUCTO PP ON MP.id_plato = PP.id_plato
        WHERE MP.id_menu = p_id_menu
        GROUP BY PP.id_producto;
    OPEN curMenu;

    -- Verificar el stock para cada producto del menú
    productoLoop: LOOP
        FETCH curMenu INTO id_producto, cantidadIngredientes;
        IF curMenu%NOTFOUND THEN
            LEAVE productoLoop;
        END IF;

        -- Verificar si hay suficiente stock para el producto
        SELECT stock INTO @stockActual FROM PRODUCTO WHERE id_producto = id_producto;
        IF @stockActual < cantidadIngredientes THEN
            SET stockSuficiente = FALSE;
            LEAVE productoLoop;
        END IF;
    END LOOP;

    CLOSE curMenu;

    IF NOT stockSuficiente THEN
        SET mensaje = 'No hay stock suficiente para uno o más productos del menú';
        LEAVE;
    END IF;

    -- Añadir el menú a la comanda y restar el stock de los productos
    INSERT INTO MENU_COMANDA (id_menu, id_comanda) VALUES (p_id_menu, p_id_comanda);

    DECLARE curIngredientes CURSOR FOR
        SELECT PP.id_producto, COUNT(*) as cantidad
        FROM MENU_PLATO MP
        INNER JOIN PLATO_PRODUCTO PP ON MP.id_plato = PP.id_plato
        WHERE MP.id_menu = p_id_menu
        GROUP BY PP.id_producto;
    OPEN curIngredientes;

    -- Restar el stock de los productos según las cantidades de ingredientes
    ingredienteLoop: LOOP
        FETCH curIngredientes INTO id_producto, cantidadIngredientes;
        IF curIngredientes%NOTFOUND THEN
            LEAVE ingredienteLoop;
        END IF;

        UPDATE PRODUCTO SET stock = stock - cantidadIngredientes WHERE id_producto = id_producto;
    END LOOP;

    CLOSE curIngredientes;

    SET mensaje = 'Ítem añadido a la comanda exitosamente';
END //

DELIMITER ;

CALL AgregarItemsComanda(1, 1, @mensaje);

SELECT @mensaje;


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

