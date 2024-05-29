# Clase de Conexión a Base de Datos (MySQL)

## Descripción
Esta clase gestiona la conexión y desconexión con una base de datos MySQL, así como la inserción de datos en las tablas correspondientes.

## Métodos

### `init()`
Inicia la conexión con la base de datos, carga los datos desde archivos CSV y luego cierra la conexión.

#### Lanza
- `CheckError`: Si hay algún error durante la conexión con la base de datos.

### `getConnection()`
Obtiene la conexión a la base de datos MySQL.

#### Valor de Retorno
- `Connection`: Objeto que representa la conexión a la base de datos.

#### Lanza
- `CheckError`: Si hay algún error durante la conexión con la base de datos.

### `cargaDeDatosCSV()`
Carga los datos desde archivos CSV en las tablas correspondientes de la base de datos.

### `insertUsuario(Usuario usuario)`
Inserta un nuevo usuario en la tabla de usuarios de la base de datos.

### Otros Métodos de Inserción
- `insertChef(Chef chef)`
- `insertCamarero(Camarero camarero)`
- `insertMesa(Mesa mesa)`
- `insertCliente(Cliente cliente)`
- `insertCliente_Mesa(Cliente_Mesa clienteMesa)`
- `insertComanda(Comanda comanda)`
- `insertMenu(Menu menu)`
- `insertMenu_Comanda(Menu_Comanda menuComanda)`
- `insertPlato(Plato plato)`
- `insertMenu_Plato(Menu_Plato menuPlato)`
- `insertProducto(Producto producto)`
- `insertPlato_Producto(Plato_Producto platoProducto)`

#### Parámetros
Todos los métodos de inserción reciben un objeto correspondiente al tipo de entidad que se va a insertar en la base de datos.

### `conectar()`
Establece la conexión con la base de datos MySQL.

#### Lanza
- `SQLException`: Si hay algún error durante la conexión con la base de datos.

### `desconectar()`
Cierra la conexión con la base de datos MySQL.

#### Lanza
- `SQLException`: Si hay algún error durante la desconexión con la base de datos.

---

# Clase de Conexión a Base de Datos (MongoDB)

## Descripción
Esta clase gestiona la conexión y desconexión con una base de datos MongoDB.

## Métodos

### `connectDB(String uri)`
Conecta con la base de datos MongoDB utilizando el URI especificado.

#### Parámetros
- `uri` (String): URI de conexión a la base de datos MongoDB.

#### Valor de Retorno
- `MongoClient`: Cliente MongoDB que representa la conexión a la base de datos.

#### Lanza
- `CheckError`: Si hay algún error durante la conexión con la base de datos.

### `disconnectDB(MongoClient mongoClient)`
Desconecta el cliente MongoDB de la base de datos.

#### Parámetros
- `mongoClient` (MongoClient): Cliente MongoDB que se va a desconectar.
