# Documentación del Planteamientos

## Verificar Usuario (Administrador)

Consulta utilizada para verificar las credenciales de un usuario administrador mediante su correo electrónico y contraseña. *Nota: Se debe crear el campo de contraseña en la tabla `USUARIO`.*

## Añadir Nuevo Cliente y Asignar Mesa Libre

Procedimiento almacenado utilizado para añadir un nuevo cliente al restaurante especificando el número de comensales y asignar automáticamente una mesa libre que cumple con las condiciones necesarias.

## Listar Mesas con Comensales

Consulta utilizada para listar todas las mesas del restaurante junto con el número de comensales que hay en cada una, ordenadas por su ID.

## Listar Comandas

Consulta utilizada para listar todas las comandas registradas en una fecha específica, ordenadas por fecha.

## Listar Personal y Asignaciones

Consultas utilizadas para listar todo el personal del restaurante (camareros y chefs) junto con las mesas o platos que tienen asignados.

## Listar Menús y Platos

Consulta utilizada para listar todos los menús del restaurante junto con los platos que contiene cada menú, ordenados por su ID.

## Gestionar Platos en Menú

Procedimiento almacenado utilizado para añadir o quitar platos de un menú especificando el identificador del menú y del plato, añadiendo si no existe la combinación y eliminando si ya existe.

## Listar Platos

Consulta utilizada para listar todos los platos del restaurante, ordenados por su nombre.

## Mostrar Ingredientes de Platos

Consulta utilizada para mostrar los ingredientes que tiene cada plato, ordenados alfabéticamente.

## Mostrar Alérgenos de Platos

Consulta utilizada para mostrar los alérgenos de los platos sin repeticiones.

## Listar Ingredientes y Stock

Consulta utilizada para listar todos los ingredientes del restaurante junto con su stock actual.

## Crear o Modificar Platos

Procedimiento almacenado utilizado para crear o modificar un plato especificando sus ingredientes.

## Verificar Stock en Nueva Comanda

Procedimiento almacenado utilizado para verificar el stock al añadir ítems a una nueva comanda, asegurando que haya suficiente stock y restando en función de los ingredientes.

## Calcular Precio Total de Comanda

Función utilizada para calcular el precio total de una comanda.

## Mostrar Facturación por Mesa y Día

Procedimiento almacenado utilizado para mostrar la facturación que ha hecho una mesa por día, con validaciones.

## Cambiar Estado de Mesa

Consulta utilizada para cambiar el estado de una mesa de disponible a no disponible o viceversa.

## Asignar/Reasignar/Desasignar Mesa a Trabajador/a

Procedimiento almacenado utilizado para asignar, reasignar o desasignar una mesa a un trabajador/a.
