# Funciones

## Función para Listar Camareros y Mesas Asignadas

Esta función devuelve una lista de todos los camareros/as del restaurante junto con las mesas que tienen asignadas.

### Ejemplo de Uso:

```sql
SELECT listarCamarerosMesas();
```

### Resultado Esperado:

```
Juan Pérez - Mesa asignada: 1
María López - Mesa asignada: 2
Pedro García - Mesa asignada: 3
```

## Función para Gestionar Platos en Menú

Esta función permite añadir o quitar platos de un menú especificando el identificador del menú y del plato. Si la combinación ya existe, se elimina; de lo contrario, se añade.

### Ejemplo de Uso:

```sql
SELECT gestionarPlatoMenu(1, 5);
```

### Resultado Esperado:

```
Plato 5 añadido al menú 1
```

## Función para Calcular Precio Total de Comanda

Esta función calcula el precio total de una comanda sumando los precios de todos los menús incluidos en la comanda.

### Ejemplo de Uso:

```sql
SELECT CalcularPrecioTotalComanda(1);
```

### Resultado Esperado:

```
78.50
```
