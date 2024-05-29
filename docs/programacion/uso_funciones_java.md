# Gestor de Usuarios

Clase que gestiona el inicio de sesión, registro y búsqueda de usuarios en la base de datos.

## Métodos

### `login`

Realiza el inicio de sesión de un usuario dado un email y una contraseña.

#### Parámetros

- `email` (String): El email del usuario.
- `contrasena` (String): La contraseña del usuario.

#### Valor de retorno

- `boolean`: `true` si el inicio de sesión fue exitoso, `false` en caso contrario.

#### Excepciones

- `CheckError`: Si hay algún error durante la verificación del usuario.

### `register`

Registra un nuevo usuario en la base de datos.

#### Parámetros

- `nombre` (String): El nombre del usuario.
- `apellido1` (String): El primer apellido del usuario.
- `apellido2` (String): El segundo apellido del usuario.
- `fechaNacimiento` (Date): La fecha de nacimiento del usuario.
- `email` (String): El email del usuario.
- `fechaRegistro` (Date): La fecha de registro del usuario.
- `telefono` (String): El número de teléfono del usuario.
- `contrasena` (String): La contraseña del usuario.

#### Valor de retorno

- `boolean`: `true` si el registro fue exitoso, `false` en caso contrario.

#### Excepciones

- `CheckError`: Si hay algún error durante el registro del usuario.

### `findByEmail`

Busca un usuario por su email en la base de datos.

#### Parámetros

- `email` (String): El email del usuario a buscar.

#### Valor de retorno

- `Usuario`: El objeto Usuario si se encuentra en la base de datos, `null` si no se encuentra.

#### Excepciones

- `CheckError`: Si hay algún error durante la búsqueda del usuario.
