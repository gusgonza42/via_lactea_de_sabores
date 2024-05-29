# Diagrama de clases

### Clase Usuario:
- Atributos: id, nombre, apellido1, apellido2, fechaNacimiento, email, fechaRegistro, telefono.
- Métodos: getNombre(), setNombre(), getApellido1(), setApellido1(), getApellido2(), setApellido2(), getFechaNacimiento(), setFechaNacimiento(), getEmail(), setEmail(), getFechaRegistro(), setFechaRegistro(), getTelefono(), setTelefono().
- Descripción: Representa a un usuario del sistema.

### Clase Cliente:
- Atributos: usuario (de tipo Usuario), comensales, numeroMesa, fechaReserva.
- Métodos: getIdUsuario(), setIdUsuario(), getComensales(), setComensales(), getNumeroMesa(), setNumeroMesa(), getFechaReserva(), setFechaReserva().
- Descripción: Representa a un cliente del restaurante.

### Clase Mesa:
- Atributos: numero, camareroAsignado, clienteAsignado, comandas.
- Métodos: getNumero(), setNumero(), getCamareroAsignado(), setCamareroAsignado(), getClienteAsignado(), setClienteAsignado(), agregarComanda(), listarComandas().
- Descripción: Representa una mesa del restaurante.

### Clase Comanda:
- Atributos: id, fecha, pagado, mesa.
- Métodos: getId(), setId(), getFecha(), setFecha(), isPagado(), setPagado(), getMesa(), setMesa().
- Descripción: Representa una orden realizada por un cliente en una mesa del restaurante.

### Clase Trabajador:
- Atributos: salario, fechaContratacion, disponible.
- Métodos: getSalario(), setSalario(), getFechaContratacion(), setFechaContratacion(), isDisponible(), setDisponible().
- Descripción: Representa a un trabajador del restaurante.

### Clase Menu:
- Atributos: id, nombre, descripcion, precio, platos.
- Métodos: getId(), setId(), getNombre(), setNombre(), getDescripcion(), setDescripcion(), getPrecio(), setPrecio(), agregarPlato(), quitarPlato(), listarPlatos().
- Descripción: Representa un menú del restaurante.

### Clase Plato:
- Atributos: id, nombre, descripcion, chef.
- Métodos: getId(), setId(), getNombre(), setNombre(), getDescripcion(), setDescripcion(), getChef(), setChef().
- Descripción: Representa un plato del menú del restaurante.

### Clase Factura:
- Atributos: id, precioTotal, fecha, comanda.
- Métodos: getId(), setId(), getPrecioTotal(), setPrecioTotal(), getFecha(), setFecha(), getComanda(), setComanda().
- Descripción: Representa la factura asociada a una comanda del restaurante.

### Clase AsignacionMesa:
- Atributos: trabajador, mesa.
- Métodos: asignarMesa(), desasignarMesa(), reasignarMesa().
- Descripción: Representa la asignación de mesas a los camareros del restaurante.

### Clase Producto:
- Atributos: id, nombre, stock, proveedor.
- Métodos: getId(), setId(), getNombre(), setNombre(), getStock(), setStock(), getProveedor(), setProveedor().
- Descripción: Representa un producto utilizado en la cocina del restaurante.

### Clase Chef:
- Descripción: Representa a un chef en el sistema. Hereda de la clase Trabajador.

### Clase Camarero:
- Descripción: Representa a un camarero en el sistema. Hereda de la clase Trabajador.