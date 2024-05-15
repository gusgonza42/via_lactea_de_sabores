/*
Listar todos los camareros/as y cocineros/as y las mesas o platos que tienen asignados (Function):
*/

-- Función para listar los camareros y mesas que tienen asignadas
DELIMITER //

-- DROP FUNCTION IF EXISTS listarCamarerosMesas;
CREATE FUNCTION listarCamarerosMesas()
RETURNS TEXT DETERMINISTIC
BEGIN
    DECLARE resultado TEXT;

    -- Concatenar camareros y mesas asignadas en una sola consulta
    SELECT GROUP_CONCAT(CONCAT(u.nombre, ' ', u.apellido1, ' ', u.apellido2, ' - Mesa asignada: ', m.num_mesa) SEPARATOR '\n') INTO resultado
    FROM USUARIO u
    INNER JOIN CAMARERO c ON u.id = c.id_usuario
    INNER JOIN MESA m ON c.id_usuario = m.id_camarero;

    -- Devolver el resultado
    RETURN resultado;
END //

-- SELECT listarCamarerosMesas();

/*
Añadir o quitar platos a un menú especificando el identificador del plato y del menú añadiendo si no existe la combinación y eliminando si ya existe (Function):
*/

-- eliminar o añadirplatos
DELIMITER //

CREATE FUNCTION gestionarPlatoMenu(menu_id INT, plato_id INT)
RETURNS TEXT DETERMINISTIC
BEGIN
    DECLARE resultado TEXT;

    -- Verificar si la combinación ya existe
    IF EXISTS (SELECT * FROM MENU_PLATO WHERE id_menu = menu_id AND id_plato = plato_id) THEN
        -- La combinación existe, eliminarla
        DELETE FROM MENU_PLATO WHERE id_menu = menu_id AND id_plato = plato_id;
        SET resultado = CONCAT('Plato ', plato_id, ' eliminado del menú ', menu_id);
    ELSE
        -- La combinación no existe, añadirla
        INSERT INTO MENU_PLATO (id_menu, id_plato) VALUES (menu_id, plato_id);
        SET resultado = CONCAT('Plato ', plato_id, ' añadido al menú ', menu_id);
    END IF;

    RETURN resultado;
END //

DELIMITER ;

-- SELECT gestionarPlatoMenu(1, 5);

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








