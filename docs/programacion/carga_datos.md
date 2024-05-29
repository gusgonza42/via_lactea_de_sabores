# Gestión de Carga de Datos desde Archivos CSV

Esta clase proporciona métodos para cargar datos desde archivos CSV y convertirlos en objetos de modelo correspondientes en la aplicación.

## Métodos

### `cargaUsuariosCSV(String rutaDelArchivoCSV)`

Carga datos de usuarios desde un archivo CSV y devuelve una lista de objetos `Usuario`.

#### Parámetros
- `rutaDelArchivoCSV` (String): Ruta del archivo CSV que contiene los datos de usuarios.

#### Retorna
- `ArrayList<Usuario>`: Lista de objetos `Usuario` cargados desde el archivo CSV.

### Otros Métodos de Carga
- `cargaChefsCSV(String rutaDelArchivoCSV)`: Carga datos de chefs desde un archivo CSV y devuelve una lista de objetos `Chef`.
- `cargaCamarerosCSV(String rutaDelArchivoCSV)`: Carga datos de camareros desde un archivo CSV y devuelve una lista de objetos `Camarero`.
- `cargaMesaCSV(String rutaDelArchivoCSV)`: Carga datos de mesas desde un archivo CSV y devuelve una lista de objetos `Mesa`.
- `cargaClientesCSV(String rutaDelArchivoCSV)`: Carga datos de clientes desde un archivo CSV y devuelve una lista de objetos `Cliente`.
- `cargaClientes_MesasCSV(String rutaDelArchivoCSV)`: Carga datos de clientes y mesas asociadas desde un archivo CSV y devuelve una lista de objetos `Cliente_Mesa`.
- `cargaComandasCSV(String rutaDelArchivoCSV)`: Carga datos de comandas desde un archivo CSV y devuelve una lista de objetos `Comanda`.
- `cargaMenuCSV(String rutaDelArchivoCSV)`: Carga datos de menús desde un archivo CSV y devuelve una lista de objetos `Menu`.
- `cargaMenu_ComandasCSV(String rutaDelArchivoCSV)`: Carga datos de menús y comandas asociadas desde un archivo CSV y devuelve una lista de objetos `Menu_Comanda`.
- `cargaPlatoCSV(String rutaDelArchivoCSV)`: Carga datos de platos desde un archivo CSV y devuelve una lista de objetos `Plato`.
- `cargaMenu_Plato(String rutaDelArchivoCSV)`: Carga datos de menús y platos asociados desde un archivo CSV y devuelve una lista de objetos `Menu_Plato`.
- `cargaProductosCSV(String rutaDelArchivoCSV)`: Carga datos de productos desde un archivo CSV y devuelve una lista de objetos `Producto`.
- `cargaPlato_Producto(String rutaDelArchivoCSV)`: Carga datos de platos y productos asociados desde un archivo CSV y devuelve una lista de objetos `Plato_Producto`.

#### Parámetros
- `rutaDelArchivoCSV` (String): Ruta del archivo CSV que contiene los datos.

#### Retorna
- Varios tipos de listas de objetos de modelo, según el método correspondiente.

## Excepciones
- `FileNotFoundException`: Si no se encuentra el archivo CSV.
- `IOException`: Si ocurre un error de entrada/salida durante la lectura del archivo.

