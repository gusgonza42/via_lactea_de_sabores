package org.ilernabarcelona.vialacteadesabores;

import java.sql.Date;

public class Cliente_Mesa {
    //ATRIBUTOS DE LA TABLA INTERMEDIA
    private int id_usuario;
    private int num_mesa;
    private Date fecha_reserva;

    //CONSTRUCTOR

    public Cliente_Mesa(int id_usuario, int num_mesa, Date fecha_reserva) {
        setId_usuario(id_usuario);
        setNum_mesa(num_mesa);
        setFecha_reserva(fecha_reserva);
    }

//GETTERS Y SETTERS

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(int num_mesa) {
        this.num_mesa = num_mesa;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }
}
