### Base de Datos: Restaurante "Vía Láctea de Sabores"

La base de datos del restaurante "Vía Láctea de Sabores" está diseñada para gestionar todas las operaciones relacionadas con la administración de un restaurante, incluyendo la gestión de usuarios, personal, mesas, clientes, comandas, menús, platos y productos.

#### Estructura de la Base de Datos

La base de datos se compone de las siguientes tablas:

1. **USUARIO**: Almacena información sobre los usuarios del sistema, como nombre, apellidos, fecha de nacimiento, correo electrónico, fecha de registro, teléfono y contraseña.

2. **CHEF**: Contiene información específica sobre los chefs del restaurante, como su salario, fecha de contratación y disponibilidad.

3. **CAMARERO**: Guarda los datos de los camareros del restaurante, incluyendo su salario, fecha de contratación y disponibilidad.

4. **MESA**: Almacena información sobre las mesas del restaurante, como su número, la fecha de registro, la disponibilidad y el máximo de comensales. También guarda la referencia al camarero asignado a cada mesa.

5. **CLIENTE**: Contiene información sobre los clientes del restaurante, como el número de comensales y la fecha de su última reserva.

6. **CLIENTE_MESA**: Es una tabla intermedia que establece la relación entre clientes y mesas, incluyendo la fecha de la reserva.

7. **COMANDA**: Almacena datos sobre las comandas realizadas por los clientes, incluyendo la fecha y si la comanda ha sido pagada. También guarda la referencia a la mesa a la que está asociada la comanda.

8. **MENU**: Guarda la información sobre los menús ofrecidos por el restaurante, incluyendo su nombre, descripción y precio.

9. **MENU_COMANDA**: Es una tabla intermedia que relaciona los menús y las comandas, incluyendo posibles alergenos.

10. **PLATO**: Contiene información sobre los platos ofrecidos por el restaurante, incluyendo su nombre, descripción y el chef responsable de su elaboración.

11. **MENU_PLATO**: Es una tabla intermedia que relaciona los menús y los platos que los componen.

12. **PRODUCTO**: Almacena información sobre los productos utilizados en la cocina del restaurante, como su nombre, stock y proveedor.

13. **PLATO_PRODUCTO**: Es una tabla intermedia que establece la relación entre platos y productos utilizados en su elaboración.
