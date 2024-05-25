package abp.project.mesapp.model;
import abp.project.mesapp.database.DataBaseConnection;
import abp.project.mesapp.util.Constantes;



import java.sql.*;
import java.util.Scanner;

public class MenuFunciones {
    private Connection conn;


    public void menuFunciones(DataBaseConnection db) throws SQLException {
        conn=db.getConn();
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
                    //verificarUsuario();
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
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 0);
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
        num_mesa=sc.nextInt();
        //Comprobamos en qué estado se encuentra
        System.out.println("cositas");


    }

    private void showFacturaciones() {
    }

    private void calculatePrice() {
    }

    private void comprobarStock() {
    }

    private void listIngredientesStock() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constantes.MOSTRAR_INGREDIENTESYSTOCK);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1)+","+rs.getString(2));

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
            System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4));
        }
    }

    private void addOrDeletePlato() {
    }

    private void listaMenuPlato() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constantes.LISTAR_MENUS_PLATOS);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4));
        }
    }

    private void listaPersonalMesaMenu() {
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
        dia=sc.nextInt();
        System.out.println("INTRODUCE MES");
        mes=sc.nextInt();
        System.out.println("INTRODUCE AÑO");
        year=sc.nextInt();
        String fechaFinal= year+"-"+mes+"-"+dia;
        ps.setString(1, fechaFinal);
        ResultSet rs = ps.executeQuery();
        System.out.println(ps);
        while (rs.next()){
            System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3));
        }
    }

    private void listarTodasLasMesas() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constantes.LISTAR_MESAS);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3));
        }
    }

    private void anadirNuevoCliente() {
    }



    private void mostrarMenu(){
        System.out.println("|---GESTOR BASE DE DATOS---|");
        System.out.println("1 Verificar usuario mediante correo electrónico y contraseña (administrador).\n" +
                "2 Añadir nuevo cliente especificando el número de comensales, se asigna a la mesa que cumple las condiciones de comensales y que está libre (Stored Procedure).\n" +
                "3 Listar todas las mesas con los comensales que hay en cada una de ellas (disponibilidad) ordenado por ID.\n" +
                "4 Lista las comandas (especificar turno y ordenar por turno).\n" +
                "5 Listar todos los camareros/as y cocineros/as y las mesas o platos que tienen asignados (Function).\n" +
                "6 Listar los menús y los platos que tiene cada menú ordenado por ID.\n" +
                "7 Añadir o quitar platos a un menú especificando el identificador del plato y del menú añadiendo si no existe la combinación y eliminando si ya existe (Function).\n" +
                "8 Listar platos ordenados por nombre.\n" +
                "9 Mostrar los ingredientes que tiene cada plato ordenados alfabéticamente.\n" +
                "10 Mostrar alérgenos de los platos sin repeticiones.\n" +
                "11 Listar todos los ingredientes y el stock que tiene.\n" +
                "12 Crear o modificar un plato especificando sus ingredientes (Stored Procedure).\n" +
                "13 Al añadir ítems a una nueva comanda, se debe verificar que haya stock y que se reste en función de los ingredientes y que no esté cerrada (Stored Procedure).\n" +
                "14 Calcular el precio total de una comanda (Function).\n" +
                "15 Mostrar la facturación que ha hecho una mesa por día (con validaciones) (Stored Procedure).\n" +
                "16 Cambiar el estado de una mesa de disponible a no disponible o viceversa.\n" +
                "17 Dado un trabajador (camarero/a) y una mesa, se debe asignar si no la tiene, desasignar si ya la tiene o reasignar si tiene una diferente." +
                "18 SALIR");
    }
}


