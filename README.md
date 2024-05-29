# VIA LACTEA DE SABORES PROJECT

## Primer paso: Init project

1. Abre el proyecto en Visual Studio Code (VSC). Asegúrate de tener instalados los plugins de Docker y Vue.js.
2. También necesitas tener IntelliJ IDEA y MySQL Workbench instalados, o simplemente IntelliJ IDEA Ultimate.

## Segundo paso: Iniciar la BBDD con el contenedor de Docker

1. Para iniciar el contenedor, ejecuta el archivo `docker-compose.yml` ubicado en `M01-SISTEMAS/docker`.
2. Haz clic derecho en `docker-compose.yml` y selecciona la opción `docker compose up`.

## Tercer paso: Abrir el proyecto de Java en IntelliJ

1. Abre IntelliJ IDEA.
2. Selecciona y abre la carpeta del proyecto que se encuentra en `M03-PROG/vialacteadesabores_backend`.

## Cuarto Paso: Cargar datos a la BBDD del contenedor de MySQL desde IntelliJ

1. Ejecuta el archivo `Main.java` ubicado en `src/main/abp/project/mesapp/util`.
2. Es normal que aparezca un mensaje de error indicando `duplicate key row 1` si ejecutas el main más de una vez.

## Quinto Paso: Encender el APIREST para activar el backend

1. En la misma ruta `src/main/abp/project/mesapp`, ejecuta el archivo `MesappApplication.java`.
2. Verás en la terminal que el APIREST se ha activado.

## Sexto Paso: Activar el frontend de Vue

1. Asegúrate de tener instalado Node.js desde [nodejs.org](https://nodejs.org/).
2. Abre la terminal y navega a la carpeta `M04-LENGUAJES/via-lactea-fronted/`.
3. Ejecuta el comando ```npm install -g @vue/cli``` y ```npm run serve```.
4. Se proporcionará un enlace al localhost donde podrás ver el frontend en funcionamiento.

YOW
