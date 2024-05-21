package abp.project.mesapp.model;


public class Menu_Comanda {
    //ATRIBUTOS
    private int id_menu;
    private int id_comanda;
    private String[] alergenos;
    //CONSTRUCTOR

    public Menu_Comanda(int id_menu, int id_comanda, String[] alergenos) {
        setId_menu(id_menu);
        setId_comanda(id_comanda);
        setAlergenos(alergenos);
    }

    //GETTERS Y SETTERS
    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    public String[] getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(String[] alergenos) {
        this.alergenos = alergenos;
    }
}

