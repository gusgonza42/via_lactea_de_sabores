package org.ilernabarcelona.constantes;

public class Constantes {
    public static final String USER = "root";
    public static final String PASS = "Sabores1234";
    public static final String SCHEMA = "vialacteadesabores";
    public static final String BBDD = "jdbc:mysql://localhost:3306/";
    public static final String DELETE_TEST = "DELETE FROM TEST WHERE ID=?";
    public static final String INSERT_TESTSABORES = "INSERT INTO USUARIOS VALUES(?,?,?,?,?,?,?,?)";
    public static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM USUARIO WHERE email = ? AND password = ?";
    public static final String asignarMesaLibre = "{CALL AsignarMesaLibre(?, ?)}";
    public static final String SELECT_MESAS_CON_COMENSALES = "SELECT m.num_mesa, m.disponibilidad, COUNT(cm.id_usuario) AS num_comensales FROM MESA m LEFT JOIN CLIENTE_MESA cm ON m.num_mesa = cm.num_mesa GROUP BY m.num_mesa ORDER BY m.num_mesa";
    public static final String SELECT_COMANDAS_BY_FECHA = "SELECT * FROM comandas WHERE fecha = ? ORDER BY fecha";
    public static final String LISTAR_CAMAREROS_MESAS = "{? = CALL listarCamarerosMesas()}";
    public static final String LISTAR_CHEFS_PLATOS = "{? = CALL listarChefsPlatos()}";
    public static final String SELECT_MENUS_PLATOS = "SELECT m.id_menu, m.nombre_menu, p.id_plato, p.nombre_plato FROM MENU m LEFT JOIN MENU_PLATO mp ON m.id_menu = mp.id_menu LEFT JOIN PLATO p ON mp.id_plato = p.id_plato ORDER BY m.id_menu";

    public static final String GESTIONAR_PLATOS_MENU = "{CALL GestionarPlatosMenu(?, ?, ?)}";
    public static final String SELECT_PLATOS_ORDER_BY_NOMBRE = "SELECT * FROM PLATO ORDER BY nombre_plato";
    public static final String SELECT_PLATOS_PRODUCTOS = "SELECT p.nombre_plato, pr.nombre FROM PLATO p INNER JOIN PLATO_PRODUCTO pp ON p.id_plato = pp.id_plato INNER JOIN PRODUCTO pr ON pp.id_producto = pr.id_producto ORDER BY p.nombre_plato, pr.nombre";
    public static final String SELECT_DISTINCT_ALERGENOS = "SELECT DISTINCT alergenos FROM MENU_COMANDA";
    public static final String SELECT_NOMBRE_STOCK = "SELECT p.nombre, p.stock FROM PRODUCTO p";
    public static final String CREAR_O_MODIFICAR_PLATO = "{CALL CrearOModificarPlato(?, ?, ?, ?, ?)}";
    public static final String VERIFICAR_STOCK = "{CALL VerificarStock(?, ?, ?)}";
    public static final String CALCULAR_PRECIO_TOTAL_COMANDA = "{? = CALL CalcularPrecioTotalComanda(?)}";
    public static final String FACTURACION_MESA_POR_DIA = "{CALL FacturacionMesaPorDia(?, ?, ?)}";
    public static final String UPDATE_DISPONIBILIDAD = "UPDATE MESA SET disponibilidad = NOT disponibilidad WHERE num_mesa = ?";
    public static final String ASIGNAR_R_D_MESA = "{CALL AsignarReasignarDesasignarMesa(?, ?, ?)}";

}
