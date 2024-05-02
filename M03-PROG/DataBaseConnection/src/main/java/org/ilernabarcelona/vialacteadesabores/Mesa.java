package org.ilernabarcelona.vialacteadesabores;

import java.sql.Date;

public class Mesa {
    //ATRIBUTOS DE LA CLASE MESA
    private int mesa;
    private int id_camarero;
    private Date fecha_registro;
    private boolean disponibilidad;
//CONSTRUCTOR


    public Mesa(int mesa, int id_camarero, Date fecha_registro, boolean disponibilidad) {
        setMesa(mesa);
        setId_camarero(id_camarero);
        setFecha_registro(fecha_registro);
        setDisponibilidad(disponibilidad);
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
}
