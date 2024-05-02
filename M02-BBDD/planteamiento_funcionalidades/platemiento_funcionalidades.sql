/*
Verificar usuario mediante correo electrónico y contraseña (administrador): nos falta crear el campo de password en la tabla usuario
*/

SELECT * FROM USUARIO WHERE email = 'correo_electronico' AND password = 'password';


/*
Añadir nuevo cliente especificando el número de comensales y asignar una mesa libre que cumple las condiciones (Stored Procedure):
*/

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
-- Llamar a la función listarCamarerosMesas
SELECT listarCamarerosMesas();

-- Llamar a la función listarChefsPlatos
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
CALL GestionarPlatosMenu(id_menu, id_plato, 'añadir/quitar');

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
CALL CrearModificarPlato(nombre_plato, descripcion,id_chef, lista_ingredientes, 'crear/modificar');

/*
Al añadir ítems a una nueva comanda, verificar que haya stock y que se reste en función de los ingredientes y que no esté cerrada (Stored Procedure):
*/

CALL VerificarStock(id_comanda, id_plato, cantidad);

/*
Calcular el precio total de una comanda (Function):

*/

SELECT CalcularPrecioTotalComanda(id_comanda);

/*
Mostrar la facturación que ha hecho una mesa por día con validaciones (Stored Procedure):
*/
CALL MostrarFacturacionMesaPorDia(num_mesa, fecha);

/*
Cambiar el estado de una mesa de disponible a no disponible o viceversa:
*/

UPDATE MESA SET disponibilidad = NOT disponibilidad WHERE num_mesa = 'introduce num_mesa';

/*
Asignar, reasignar o desasignar mesa a un trabajador/a:

*/
CALL AsignarReasignarDesasignarMesa(id_trabajador, num_mesa, 'asignar/reasignar/desasignar');

