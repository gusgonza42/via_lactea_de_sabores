package abp.project.mesapp.model;

import java.sql.Date;

public class Mesa {
    //ATRIBUTOS DE LA CLASE MESA
    public int mesa;
    public int id_camarero;
    public Date fecha_registro;
    public boolean disponibilidad;
    public int max_comensales;
//CONSTRUCTOR


    public Mesa(int mesa, int id_camarero, Date fecha_registro, boolean disponibilidad, int max_comensales){
        setMesa(mesa);
        setId_camarero(id_camarero);
        setFecha_registro(fecha_registro);
        setDisponibilidad(disponibilidad);
        setMax_comensales(max_comensales);
    }
    public Mesa (){

    }

    //GETTERS Y SETTERS DE LA CLASE MESA
    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getId_camarero() {
        return id_camarero;
    }

    public void setId_camarero(int id_camarero) {
        this.id_camarero = id_camarero;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getMax_comensales() {
        return max_comensales;
    }
    public void setMax_comensales(int max_comensales) {
        this.max_comensales = max_comensales;
    }
}
