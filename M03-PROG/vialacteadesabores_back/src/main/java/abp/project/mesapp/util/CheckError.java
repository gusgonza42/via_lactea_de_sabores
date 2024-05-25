package abp.project.mesapp.util;

public class CheckError extends Exception {

    public static final int ERROR_USUARIO_NO_EXISTE = 0;
    public static final int ERROR_MESA_NO_DISPONIBLE_RESERVA = 1;
    public static final int ERROR_LISTAR_MESAS = 2;
    public static final int ERROR_LISTAR_COMANDAS = 3;
    public static final int ERROR_LISTAR_PERSONAL = 4;
    public static final int ERROR_LISTAR_MENUS = 5;
    public static final int ERROR_GESTIONAR_PLATOS_MENU = 6;
    public static final int ERROR_LISTAR_PLATOS = 7;
    public static final int ERROR_GESTIONAR_PLATO = 8;
    public static final int ERROR_GESTIONAR_STOCK_COMANDA = 9;
    public static final int ERROR_CALCULAR_PRECIO_COMANDA = 10;
    public static final int ERROR_MOSTRAR_FACTURACION_MESA = 11;
    public static final int ERROR_CAMBIAR_ESTADO_MESA = 12;
    public static final int ERROR_ASIGNAR_REASIGNAR_DESASIGNAR_MESA = 13;
    public static final int ERROR_CREAR_MODIFICAR_PLATO = 14;
    public static final int ERROR_VERIFICAR_STOCK = 15;
    public static final int ERROR_MOSTRAR_INGREDIENTES_PLATO = 16;
    public static final int ERROR_MOSTRAR_ALERGENOS_PLATO = 17;
    public static final int ERROR_NO_ES_MAIL = 18;
    public static final int ERROR_BBDD_FAIL = 19;
    public static final int ERROR_CREAR_ADMIN_USER = 20;
    public static final int ERROR_LECTURA_DATOS = 21;
    public static final int ERROR_MESA_NO_DISPONIBLE = 22;
    public static final int ERROR_ASIGNACION_MESA = 23;
    public static final int ERROR_CERRAR_RECURSOS_BBDD = 24;
    public static final int ERROR_USER = 25;
    public static final int ERROR_CONNECT_BBDD = 26;
    public static final int ERROR_USER_EXISTS = 27;
    public static final int ERROR_BBDD_FAIL_INSERT_NEW_USER = 28;


    private final int errorCode;
    public static final String[] errorPrint = {
            "ERROR 000: El usuario no existe o la contraseña es incorrecta",
            "ERROR 001: No hay mesas disponibles en la fecha elegida",
            "ERROR 002: Error al listar las mesas",
            "ERROR 003: Error al listar las comandas",
            "ERROR 004: Error al listar el personal",
            "ERROR 005: Error al listar los menús",
            "ERROR 006: Error al gestionar los platos del menú",
            "ERROR 007: Error al listar los platos",
            "ERROR 008: Error al gestionar el plato",
            "ERROR 009: Error al gestionar el stock de la comanda",
            "ERROR 010: Error al calcular el precio de la comanda",
            "ERROR 011: Error al mostrar la facturación de la mesa",
            "ERROR 012: Error al cambiar el estado de la mesa",
            "ERROR 013: Error al asignar, reasignar o desasignar la mesa",
            "ERROR 014: Error al crear o modificar el plato",
            "ERROR 015: Error al verificar el stock",
            "ERROR 016: Error al mostrar los ingredientes del plato",
            "ERROR 017: Error al mostrar los alérgenos del plato",
            "ERROR 018: ERROR el email no es válido",
            "ERROR 019: ERROR CON LA BBDD",
            "ERROR 020: Error al crear el admin user",
            "ERROR 021: Error en la lectura de datos",
            "ERROR 022: Mesas no disponibles en este momento",
            "ERROR 023: Error al asignar la mesa",
            "ERROR 024: Error al cerrar los recursos con la BBDD",
            "ERROR 025: Error en al registrar usuario",
            "ERROR 026: Error al establecer conexión con la BBDD",
            "ERROR 027: Error el usuario ya existe",
            "ERROR 028: Error en la BBDD al inserta el nuevo usuario"
    };

    public CheckError(int code) {
        this.errorCode = code;
    }

    public String getMessage() {
        return errorPrint[this.errorCode];
    }
}