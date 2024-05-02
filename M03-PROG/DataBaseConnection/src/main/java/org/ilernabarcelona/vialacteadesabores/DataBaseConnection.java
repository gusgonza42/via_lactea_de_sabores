package org.ilernabarcelona.vialacteadesabores;

import org.ilernabarcelona.files.FileManager;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnection {
    public static final String USER = "root";
    public static final String PASS = "Sabores1234";
    public static final String SCHEMA = "vialacteadesabores";
    public static final String BBDD = "jdbc:mysql://localhost:3307/";
    public static final String PATH="src/main/java/org/ilernabarcelona/files/csv/";
    private Connection conn;
    public ArrayList<Usuario> usuarios;
    public static final String INSERT_USUARIO = "INSERT INTO USUARIO VALUES(?,?,?,?,?,?,?,?,?)";
    public ArrayList<Chef> chefs;
    public static final String INSERT_CHEF ="INSERT INTO CHEF VALUES(?,?,?,?)";
    public ArrayList<Camarero> camareros;
    public static final String INSERT_CAMARERO ="INSERT INTO CAMARERO VALUES(?,?,?,?)";
    public ArrayList<Mesa> mesas;
    public static final String INSERT_MESA ="INSERT INTO MESA VALUES(?,?,?,?)";
    public ArrayList<Cliente> clientes;
    public static final String INSERT_CLIENTE ="INSERT INTO CLIENTE VALUES(?,?,?)";
    public ArrayList<Cliente_Mesa> clientes_mesas;
    public static final String INSERT_CLIENTE_MESA ="INSERT INTO CLIENTE_MESA VALUES(?,?,?)";
    public ArrayList<Comanda> comandas;
    public static final String INSERT_COMANDA ="INSERT INTO COMANDA VALUES(?,?,?,?)";
    public ArrayList<Menu> menus;
    public static final String INSERT_MENU="INSERT INTO MENU VALUES(?,?,?,?)";
    public ArrayList<Menu_Comanda> menus_comandas;
    public static final String INSERT_MENU_COMANDAS="INSERT INTO MENU_COMANDA VALUES(?,?,?)";
    public ArrayList<Plato> platos;
    public static final String INSERT_PLATO ="INSERT INTO PLATO VALUES(?,?,?,?)";
    public ArrayList<Menu_Plato> menus_platos;
    public static final String INSERT_MENU_PLATO="INSERT INTO MENU_PLATO VALUES(?,?)";
    public ArrayList<Producto> productos;
    public static final String INSERT_PRODUCTO ="INSERT INTO PRODUCTO VALUES(?,?,?,?)";
    public ArrayList<Plato_Producto> platos_productos;
    public static final String INSERT_PLATOS_PRODUCTOS="INSERT INTO PLATO_PRODUCTO VALUES(?,?)";


    public void init() throws SQLException {
        //Nos conectamos
        conn = DriverManager.getConnection(BBDD + SCHEMA, USER, PASS);
        //Cargamos datos
        cargaDeDatosCSV();
        System.out.println("CARGA DE DATOS COMPLETADA :)!");
        // DESCONECTAR
        conn.close();
    }
    private void cargaDeDatosCSV() throws SQLException {
        //Creamos las rutas hacia el csv
        String rutaDelArchivoCSVUsuario=PATH + "Usuarios.csv";
        String rutaDelArchivoCSVChef=PATH + "Chefs.csv";
        String rutaDelArchivoCSVCamarero=PATH + "Camareros.csv";
        String rutaDelArchivoCSVMesa= PATH + "Mesas.csv";
        String rutaDelArchivoCSVCliente= PATH +"Clientes.csv";
        String rutaDelArchivoCSVClienteMesa= PATH +"Clientes_Mesas.csv";
        String rutaDelArchivoCSVComanda=PATH +"Comandas.csv";
        String rutaDelArchivoCSVMenu=PATH +"Menus.csv";
        String rutaDelArchivoCSVMenuComanda=PATH +"Menus_Comandas.csv";
        String rutaDelArchivoCSVPlato=PATH +"Platos.csv";
        String rutaDelArchivoCSVMenuPlato =PATH +"Menus_Platos.csv";
        String rutaDelArchivoCSVProductos =PATH +"Productos.csv";
        String rutaDelArchivoCSVPlatoProducto =PATH +"Platos_Productos.csv";
        //Instanciamos el fileManager
        FileManager fm = new FileManager();
        //USUARIOS
        usuarios = fm.cargaUsuariosCSV(rutaDelArchivoCSVUsuario);
        for (Usuario usuario : usuarios) {
            //Añadimos las querys
            insertUsuario(usuario);
        }
        //CHEFS
        chefs = fm.cargaChefsCSV(rutaDelArchivoCSVChef);
        for (Chef chef : chefs) {
            //Añadimos las querys
            insertChef(chef);
        }
        //CAMARERO
        camareros = fm.cargaCamarerosCSV(rutaDelArchivoCSVCamarero);
        for (Camarero camarero : camareros) {
            //Añadimos las querys
            insertCamarero(camarero);
        }
        //MESA
        mesas = fm.cargaMesaCSV(rutaDelArchivoCSVMesa);
        for (Mesa mesa : mesas) {
            //Añadimos las querys
            insertMesa(mesa);
        }
        //CLIENTE
        clientes = fm.cargaClientesCSV(rutaDelArchivoCSVCliente);
        for (Cliente cliente : clientes) {
            //Añadimos las querys
            insertCliente(cliente);
        }
        //CLIENTE_MESA
        clientes_mesas = fm.cargaClientes_MesasCSV(rutaDelArchivoCSVClienteMesa);
        for (Cliente_Mesa cliente_Mesa : clientes_mesas) {
            insertCliente_Mesa(cliente_Mesa);
        }
        //COMANDA
        comandas = fm.cargaComandasCSV(rutaDelArchivoCSVComanda);
        for (Comanda comanda : comandas) {
            insertComanda(comanda);
        }
        //MENU
        menus = fm.cargaMenuCSV(rutaDelArchivoCSVMenu);
        for (Menu menu : menus){
            insertMenu(menu);
        }
        //MENU_COMANDA
        menus_comandas = fm.cargaMenu_ComandasCSV(rutaDelArchivoCSVMenuComanda);
        for (Menu_Comanda menu_comanda : menus_comandas){
            insertMenu_Comanda(menu_comanda);
        }
        //PLATO
        platos = fm.cargaPlatoCSV(rutaDelArchivoCSVPlato);
        for (Plato plato : platos){
            insertPlato(plato);
        }
        //MENU_PLATO
        menus_platos = fm.cargaMenu_Plato(rutaDelArchivoCSVMenuPlato);
        for (Menu_Plato menuPlato : menus_platos){
            insertMenu_Plato(menuPlato);
        }
        //PRODUCTO
        productos = fm.cargaProductosCSV(rutaDelArchivoCSVProductos);
        for (Producto producto : productos) {
            insertProducto(producto);
        }
        //PLATO PRODUCTO
        platos_productos = fm.cargaPlato_Producto(rutaDelArchivoCSVPlatoProducto);
        for (Plato_Producto platoProducto : platos_productos){
            insertPlato_Producto(platoProducto);
        }


    }


    // INSERT BBDD
    //USUARIO
    private void insertUsuario(Usuario usuario) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_USUARIO);
        ps.setInt(1, usuario.getId_usuario());
        // NOMBRE
        ps.setString(2, usuario.getNombre());
        // APPELLIDO1
        ps.setString(3, usuario.getApellido1());
        // APELLIDO2
        ps.setString(4, usuario.getApellido2());
        // FECHA_NACIMIENTO
        ps.setDate(5, usuario.getFecha_nacimiento());
        // EMAIL
        ps.setString(6, usuario.getEmail());
        // FECHA_REGISTRO
        ps.setDate(7, usuario.getFecha_registro());
        // TELEFONO
        ps.setString(8, usuario.getTelefono());
        //CONTRASEÑA
        ps.setString(9,usuario.getContrasena());
        System.out.println(ps);
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR: no hay nada que actualizar");
        }

    }
    //CHEF
    public void insertChef(Chef chef) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_CHEF);
        //ID_USUARIO
        ps.setInt(1,chef.getId_usuario());
        //SALARIO
        ps.setDouble(2,chef.getSalario());
        //FECHA_CONTRATACION
        ps.setDate(3,chef.getFechaContratacion());
        //DISPONIBLE
        ps.setBoolean(4,chef.isDisponible());
        System.out.println(ps);
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR: no hay nada que actualizar");
        }
    }
    //CAMARERO
    private void insertCamarero(Camarero camarero) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_CAMARERO);
        //ID_USUARIO
        ps.setInt(1,camarero.getId_usuario());
        //SALARIO
        ps.setDouble(2,camarero.getSalario());
        //FECHA_CONTRATACION
        ps.setDate(3,camarero.getFecha_contratacion());
        //DISPONIBLE
        ps.setBoolean(4,camarero.isDisponibilidad());
        System.out.println(ps);
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR: no hay nada que actualizar");
        }
    }
    //MESA
    private void insertMesa(Mesa mesa) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_MESA);
        //NUM_MESA
        ps.setInt(1,mesa.getMesa());
        //ID_CAMARERO
        ps.setInt(2,mesa.getId_camarero());
        //FECHA_REGISTRO
        ps.setDate(3,mesa.getFecha_registro());
        //DISPONIBILIDAD
        ps.setBoolean(4,mesa.isDisponibilidad());
        System.out.println(ps);
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR: no hay nada que actualizar");
        }

    }
    //CLIENTE
    private void insertCliente(Cliente cliente) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_CLIENTE);
        //ID_USUARIO
        ps.setInt(1, cliente.getId_usuario());
        //COMENSALES
        ps.setInt(2, cliente.getComensales());
        //FECHA_ULTIMARESERVA
        ps.setDate(3, cliente.getFecha_ultimaReserva());
        System.out.println(ps);
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR: no hay nada que actualizar");
        }
    }
     //CLIENTE_MESA
     private void insertCliente_Mesa(Cliente_Mesa clienteMesa) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_CLIENTE_MESA);
        //ID_USUARIO
         ps.setInt(1,clienteMesa.getId_usuario());
        //NUM_MESA
         ps.setInt(2,clienteMesa.getNum_mesa());
         // FECHA_RESERVA
         ps.setDate(3,clienteMesa.getFecha_reserva());
         System.out.println(ps);
         int result = ps.executeUpdate();
         if (result == 0) {
             System.out.println("ERROR: no hay nada que actualizar");
         }
     }
     //COMANDA
     private void insertComanda(Comanda comanda) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_COMANDA);
        //ID_COMANDA
         ps.setInt(1,comanda.getId_comanda());
         //FECHA
         ps.setDate(2,comanda.getFecha());
         //PAGADO
         ps.setBoolean(3,comanda.isPagado());
         //NUM_MESA
         ps.setInt(4,comanda.getNum_mesa());
         System.out.println(ps);
         int result = ps.executeUpdate();
         if (result == 0) {
             System.out.println("ERROR: no hay nada que actualizar");
         }
     }
     //MENU
     private void insertMenu(Menu menu) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_MENU);
         //ID_MENU
         ps.setInt(1,menu.getId_menu());
         //NOMBRE_MENU
         ps.setString(2,menu.getNombre());
         //DESCRIPCION
         ps.setString(3,menu.getDescripcion());
         //PRECIO
         ps.setDouble(4,menu.getPrecio());
         System.out.println(ps);
         int result = ps.executeUpdate();
         if (result == 0) {
             System.out.println("ERROR: no hay nada que actualizar");
         }
     }
    //MENU_COMANDA
    private void insertMenu_Comanda(Menu_Comanda menuComanda) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_MENU_COMANDAS);
        //ID_MENU
        ps.setInt(1,menuComanda.getId_menu());
        //ID_COMANDA
        ps.setInt(2,menuComanda.getId_comanda());
        //ALERGENOS
        ps.setString(3, toString());
        System.out.println(ps);
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR: no hay nada que actualizar");
        }
    }
    //PLATO
    private void insertPlato(Plato plato) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_PLATO);
        //ID_PLATO
        ps.setInt(1,plato.getId_plato());
        //NOMBRE_PLATO
        ps.setString(2,plato.getNombre_plato());
        //DESCRIPCION
        ps.setString(3,plato.getDescripcion());
        //ID_CHEF
        ps.setInt(4,plato.getId_chef());
        System.out.println(ps);
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR: no hay nada que actualizar");
        }
    }
    //MENU_PLATO
    private void insertMenu_Plato(Menu_Plato menuPlato) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_MENU_PLATO);
        //ID_MENU
        ps.setInt(1,menuPlato.getId_menu());
        //ID_PLATO
        ps.setInt(2,menuPlato.getId_plato());
        System.out.println(ps);
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR: no hay nada que actualizar");
        }
    }

    //PRODUCTO
    private void insertProducto(Producto producto) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_PRODUCTO);
        //ID_PRODUCTO
        ps.setInt(1,producto.getIdproducto());
        //NOMBRE
        ps.setString(2,producto.getNombre());
        //STOCK
        ps.setInt(3,producto.getStock());
        //PROVEEDOR
        ps.setString(4,producto.getProveedor());
        System.out.println(ps);
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR: no hay nada que actualizar");
        }
    }
    //PLATO PRODUCTO
    private void insertPlato_Producto(Plato_Producto platoProducto) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_PLATOS_PRODUCTOS);
        //ID_PLATO
        ps.setInt(1,platoProducto.getId_plato());
        //ID_PRODUCTO
        ps.setInt(2,platoProducto.getId_producto());
        System.out.println(ps);
        int result = ps.executeUpdate();
        if (result == 0) {
            System.out.println("ERROR: no hay nada que actualizar");
        }
    }
}

