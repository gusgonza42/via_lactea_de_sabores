package abp.project.mesapp.model;

public class Menu {
    //ATRIBUTOS DE LA TABLA MENU
    private int id_menu;
    private String nombre;
    private String descripcion;
    private double precio;

    //CONSTRUCTOR

    public Menu(int id_menu, String nombre, String descripcion, double precio) {
        setId_menu(id_menu);
        setNombre(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
    }


    //GETTERS Y SETTERS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
