package abp.project.mesapp.vialacteadesabores;

public class Menu_Plato {
    //ATRIBUTOS
    private int id_menu;
    private int id_plato;
    //CONSTRUCTOR

    public Menu_Plato(int id_menu, int id_plato) {
        setId_menu(id_menu);
        setId_plato(id_plato);
    }

    //GETTERS Y SETTERS


    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public int getId_plato() {
        return id_plato;
    }

    public void setId_plato(int id_plato) {
        this.id_plato = id_plato;
    }
}
