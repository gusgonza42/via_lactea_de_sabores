
1. **Construcción de la imagen Docker:**

   Utiliza el siguiente comando para construir la imagen usando Docker compose:

   ```bash
   docker-compose up --build
   ```

   Este comando asegurará que la imagen Docker se construya correctamente antes de iniciar el contenedor.
   
2. **En caso de que el puerto 3306 esté ocupado:**

    - Identifica los procesos que utilizan el puerto 3306:

      Abre una ventana del símbolo del sistema en modo administrador y ejecuta el siguiente comando para encontrar los procesos que están utilizando el puerto 3306:

      ```bash
      netstat -ano | findstr :3306
      ```

      Esto mostrará una lista de conexiones activas y los PID (identificadores de proceso) asociados a los procesos que están utilizando el puerto 3306.

    - Detén el proceso usando el PID:

      Una vez que hayas identificado el PID del proceso que deseas detener, puedes utilizar el siguiente comando para detenerlo:

      ```bash
      taskkill /PID <PID> /F
      ```

      Reemplaza `<PID>` con el número de identificación del proceso que deseas detener. Por ejemplo:

      ```bash
      taskkill /PID 1234 /F
      ```

      Donde 1234 es el PID del proceso que deseas detener.

2. **Acceder a MySQL desde fuera del contenedor:**

   Una vez que el contenedor esté en funcionamiento, puedes acceder a MySQL desde fuera del contenedor utilizando el host y el puerto 3306. Puedes usar 'localhost' o '127.0.0.1' como nombre del host y la contraseña de root.
