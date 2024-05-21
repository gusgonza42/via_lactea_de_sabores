package abp.project.mesapp.model;

public class Plato_Producto {
    //ATRIBUTOS
    private int id_plato;
    private int id_producto;
    //CONSTRUCTOR

    public Plato_Producto(int id_plato, int id_producto) {
        setId_producto(id_producto);
        setId_plato(id_plato);
    }


    //GETTERS Y SETTERS

    public int getId_plato() {
        return id_plato;
    }

    public void setId_plato(int id_plato) {
        this.id_plato = id_plato;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
}
