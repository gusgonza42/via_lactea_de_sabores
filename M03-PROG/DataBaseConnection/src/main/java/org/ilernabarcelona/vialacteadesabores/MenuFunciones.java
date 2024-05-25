package org.ilernabarcelona.vialacteadesabores;

import org.ilernabarcelona.checkExceptions.CheckError;
import org.ilernabarcelona.constantes.Constantes;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuFunciones {
    private Connection conn;


    public void menuFunciones(DataBaseConnection db) throws SQLException, CheckError {
        conn = db.getConn();
        db.conectar();

        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    //FUNCIONA
                    verificarUsuario();
                    break;
                case 2:
                    anadirNuevoCliente();
                    break;
                case 3:
                    //NO FUNCIONA
                    listarTodasLasMesas();
                    break;
                case 4:
                    listaComandas();
                    break;
                case 5:
                    listaPersonalMesaMenu();
                    break;
                case 6:
                    //FUNCIONA
                    listaMenuPlato();
                    break;
                case 7:
                    addOrDeletePlato();
                    break;
                case 8:
                    //FUNCIONA
                    platosList();
                    break;
                case 9:
                    showIngredientesPlatos();
                    break;
                case 10:
                    showAlergenosPlatos();
                    break;
                case 11:
                    listIngredientesStock();
                    break;
                case 12:
                    modificarPlato();
                    break;
                case 13:
                    comprobarStock();
                    break;
                case 14:
                    calculatePrice();
                    break;
                case 15:
                    showFacturaciones();
                    break;
                case 16:
                    changeStageMesa();
                    break;
                case 17:
                    asignarCamareroMesa();
                    break;
                case 18:

                    crearAdminUser();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }

    private void crearAdminUser() throws CheckError {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese el tipo de usuario (ADMIN, ENCARGADO, USER): ");
            String tipoUsuario = sc.nextLine().toUpperCase(); // Convertir a mayúsculas
            sc.close();
            String userIs;
            if (tipoUsuario.equals("ADMIN")) {
                userIs = Constantes.CREAR_USER_ADMIN;
            } else if (tipoUsuario.equals("ENCARGADO")) {
                userIs = Constantes.CREAR_USER_ENCARGADO;
            } else if (tipoUsuario.equals("USER")) {
                userIs = Constantes.CREAR_USER_NORMAL;
            } else {
                throw new CheckError(CheckError.ERROR_LECTURA_DATOS);
            }

            PreparedStatement ps = conn.prepareStatement(userIs);

            // Ejecutar la consulta
            boolean success = ps.execute();
            if (!success) {
                throw new CheckError(CheckError.ERROR_CREAR_ADMIN_USER);
            }
            System.out.println("Usuario " + tipoUsuario + " creado exitosamente");
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
    }


    private void modificarPlato() {
    }

    private void asignarCamareroMesa() {
    }

    private void changeStageMesa() throws SQLException {
        //FALTA POR HACER
        Scanner sc = new Scanner(System.in);
        PreparedStatement ps = conn.prepareStatement(Constantes.CAMBIO_DISPONIBILIDAD);
        System.out.println("INTRODUCE EL NUMERO DE MESA");
        int num_mesa;
        num_mesa = sc.nextInt();
        //Comprobamos en qué estado se encuentra
        System.out.println("cositas");


    }

    private void showFacturaciones() throws CheckError {
        try {
            CallableStatement cs = conn.prepareCall(Constantes.FACTURACION_MESA_POR_DIA);
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el id de la mesa: ");
            int id_mesa = sc.nextInt();
            if (mesaDisponible(id_mesa)) {
                throw new CheckError(CheckError.ERROR_MOSTRAR_FACTURACION_MESA);
            }
            System.out.println("Introduce la fecha: ");
            String fecha = sc.nextLine();
            cs.setInt(1, id_mesa);
            cs.setString(2, fecha);
            cs.execute();
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
    }

    private void calculatePrice() throws CheckError {
        try {
            CallableStatement cs = conn.prepareCall(Constantes.CALCULAR_PRECIO_TOTAL_COMANDA);
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el id de la comanda: ");
            int id_comanda = sc.nextInt();
            if (id_comandaExists(id_comanda)) {
                throw new CheckError(CheckError.ERROR_CALCULAR_PRECIO_COMANDA);
            }
            cs.setInt(1, id_comanda);
            cs.execute();
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
    }

    private boolean id_comandaExists(int idComanda) throws CheckError {
        try {
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_COUNT_FROM_MESAS);
            ps.setInt(1, idComanda);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) <= 0;
            }
            return true;
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
    }

    private void comprobarStock() throws CheckError {
        try {
            CallableStatement cs = conn.prepareCall(Constantes.VERIFICAR_STOCK);
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el id del plato: ");
            int id_plato = sc.nextInt();
            if (id_platoExists(id_plato)) {
                throw new CheckError(CheckError.ERROR_VERIFICAR_STOCK);
            }
            System.out.println("Introduce el id de la comanda: ");
            int id_comanda = sc.nextInt();
            if (id_comandaExists(id_comanda)) {
                throw new CheckError(CheckError.ERROR_VERIFICAR_STOCK);
            }
            System.out.println("Introduce el id del producto: ");
            int id_producto = sc.nextInt();
            cs.setInt(1, id_plato);
            cs.setInt(2, id_comanda);
            cs.setInt(3, id_producto);
            cs.execute();
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }

    }

    private void listIngredientesStock() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constantes.MOSTRAR_INGREDIENTESYSTOCK);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + "," + rs.getString(2));

        }

    }

    private void showAlergenosPlatos() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constantes.MOSTRAR_ALERGENOS);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
        }
    }

    private void showIngredientesPlatos() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constantes.MOSTRAR_INGREDIENTES);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
        }
    }

    private void platosList() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constantes.LISTAR_PLATOS_NOMBRE);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4));
        }
    }

    private void addOrDeletePlato() throws CheckError {
        try {
            CallableStatement cs = conn.prepareCall(Constantes.GESTIONAR_PLATOS_MENU);
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el id del plato: ");
            int id_plato = sc.nextInt();
            if (id_platoExists(id_plato)) {
                throw new CheckError(CheckError.ERROR_GESTIONAR_PLATOS_MENU);
            }
            System.out.println("Introduce el id del menu: ");
            int id_menu = sc.nextInt();
            System.out.println("Introduce 1 para añadir o 0 para eliminar: ");
            int add = sc.nextInt();
            cs.setInt(1, id_plato);
            cs.setInt(2, id_menu);
            cs.setInt(3, add);
            cs.execute();
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
    }

    private boolean id_platoExists(int idPlato) {
        try {
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_PLATOS_ORDER_BY_NOMBRE);
            ps.setInt(1, idPlato);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) <= 0;
            }
            return true;
        } catch (SQLException e) {
            return true;
        }
    }

    private void listaMenuPlato() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constantes.LISTAR_MENUS_PLATOS);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4));
        }
    }

    private void listaPersonalMesaMenu() throws CheckError {
        try {
            CallableStatement cs = conn.prepareCall(Constantes.LISTAR_CAMAREROS_MESAS);
            System.out.println(cs);
            ResultSet rs = cs.executeQuery();
            if (!rs.next()) {
                throw new CheckError(CheckError.ERROR_LISTAR_PERSONAL);
            }
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
    }

    private void listaComandas() throws SQLException {
        Scanner sc = new Scanner(System.in);
        PreparedStatement ps = conn.prepareStatement(Constantes.LISTAR_COMANDAS);
        System.out.println(ps);
        System.out.println("INTRODUCE FECHA");
        int dia;
        int mes;
        int year;
        System.out.println("INTRODUCE DIA");
        dia = sc.nextInt();
        System.out.println("INTRODUCE MES");
        mes = sc.nextInt();
        System.out.println("INTRODUCE AÑO");
        year = sc.nextInt();
        String fechaFinal = year + "-" + mes + "-" + dia;
        ps.setString(1, fechaFinal);
        ResultSet rs = ps.executeQuery();
        System.out.println(ps);
        while (rs.next()) {
            System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
        }
    }

    private void listarTodasLasMesas() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constantes.LISTAR_MESAS);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
        }
    }

    private void anadirNuevoCliente() throws CheckError {
        try {
            PreparedStatement ps = conn.prepareStatement(Constantes.ASIGNAR_MESA_LIBRE);
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el id del usuario: ");
            int id_usuario = sc.nextInt();
            if (!usuarioExists(id_usuario)) {//si el user no existe
                throw new CheckError(CheckError.ERROR_USUARIO_NO_EXISTE);
            }
            System.out.println("Introduce el número de comensales: ");
            int num_comensales = sc.nextInt();
            if (mesaDisponible(num_comensales)) { //si la mesa no esta disponible
                throw new CheckError(CheckError.ERROR_MESA_NO_DISPONIBLE);
            }
            ps.setInt(1, id_usuario);
            ps.setInt(2, num_comensales);
            ps.execute();
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);// ERROR GENERAL
        }
    }

    public boolean usuarioExists(int id_usuario) throws CheckError {
        try {
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_COUNT_FROM_USUARIO);
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
    }

    public boolean mesaDisponible(int num_comensales) throws CheckError {

        try {
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_COUNT_FROM_MESAS);
            ps.setInt(1, num_comensales);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) <= 0;
            }
            return true;
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
    }


    private void verificarUsuario() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_USUARIO);
        System.out.println(ps.toString());
        Scanner sc = new Scanner(System.in);
        String usuario;
        String contrasena;
        System.out.println("Ingrese su usuario: ");
        usuario = sc.nextLine();
        System.out.println("Ingrese su contrasena: ");
        contrasena = sc.nextLine();
        //Hacemos la consulta
        ps.setString(1, usuario);
        ps.setString(2, contrasena);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Usuario existente");
        } else {
            System.out.println("Usuario no existe");
        }
        System.out.println(ps);
    }

    private void mostrarMenu() {
        System.out.println("|---GESTOR BASE DE DATOS---|");
        System.out.println("""
            1 Verificar usuario mediante correo electrónico y contraseña (administrador).
            2 Añadir nuevo cliente especificando el número de comensales, se asigna a la mesa que cumple las condiciones de comensales y que está libre (Stored Procedure).
            3 Listar todas las mesas con los comensales que hay en cada una de ellas (disponibilidad) ordenado por ID.
            4 Lista las comandas (especificar turno y ordenar por turno).
            5 Listar todos los camareros/as y cocineros/as y las mesas o platos que tienen asignados (Function).
            6 Listar los menús y los platos que tiene cada menú ordenado por ID.
            7 Añadir o quitar platos a un menú especificando el identificador del plato y del menú añadiendo si no existe la combinación y eliminando si ya existe (Function).
            8 Listar platos ordenados por nombre.
            9 Mostrar los ingredientes que tiene cada plato ordenados alfabéticamente.
            10 Mostrar alérgenos de los platos sin repeticiones.
            11 Listar todos los ingredientes y el stock que tiene.
            12 Crear o modificar un plato especificando sus ingredientes (Stored Procedure).
            13 Al añadir ítems a una nueva comanda, se debe verificar que haya stock y que se reste en función de los ingredientes y que no esté cerrada (Stored Procedure).
            14 Calcular el precio total de una comanda (Function).
            15 Mostrar la facturación que ha hecho una mesa por día (con validaciones) (Stored Procedure).
            16 Cambiar el estado de una mesa de disponible a no disponible o viceversa.
            17 Dado un trabajador (camarero/a) y una mesa, se debe asignar si no la tiene, desasignar si ya la tiene o reasignar si tiene una diferente.
            18 Crear ADMINISTRADOR
            19 SALIR
            """);
    }

}


