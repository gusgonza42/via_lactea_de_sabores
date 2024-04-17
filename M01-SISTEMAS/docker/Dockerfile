# Usa la imagen base desde Docker Hub
FROM gusgonza/via-lactea-de-sabores

# Exponemos el puerto 3306 para que se pueda acceder a MySQL desde fuera del contenedor
EXPOSE 3306

# Establecemos las variables de entorno para MySQL
ENV MYSQL_ROOT_PASSWORD=Sabores1234

# Copia el archivo SQL al directorio de inicialización de MySQL
COPY ../../M02-BBDD/script/script_inicial.sql /docker-entrypoint-initdb.d/
