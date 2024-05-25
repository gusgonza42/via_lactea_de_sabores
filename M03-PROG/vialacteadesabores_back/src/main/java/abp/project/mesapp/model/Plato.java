package abp.project.mesapp.model;

public class Plato {
    //ATRIBUTOS DE LA CLASE PLATO
    private int id_plato;
    private String nombre_plato;
    private String descripcion;
    private int id_chef;
    //CONSTRUCTOR

    public Plato(int id_plato, String nombre_plato, String descripcion, int id_chef) {
        setId_plato(id_plato);
        setNombre_plato(nombre_plato);
        setDescripcion(descripcion);
        setId_chef(id_chef);
    }


    //GETTERS Y SETTERS


    public int getId_plato() {
        return id_plato;
    }

    public void setId_plato(int id_plato) {
        this.id_plato = id_plato;
    }

    public String getNombre_plato() {
        return nombre_plato;
    }

    public void setNombre_plato(String nombre_plato) {
        this.nombre_plato = nombre_plato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_chef() {
        return id_chef;
    }

    public void setId_chef(int id_chef) {
        this.id_chef = id_chef;
    }
}
