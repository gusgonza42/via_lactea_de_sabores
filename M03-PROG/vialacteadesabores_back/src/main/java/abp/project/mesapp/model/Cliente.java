package abp.project.mesapp.model;

import java.sql.Date;

public class Cliente extends Usuario{
    //ATRIBUTOS DE LA CLASE CLIENTE

    private int comensales;
    private Date fecha_ultimaReserva;
//CONSTRUCTOR


    public Cliente(int id_usuario, int comensales, Date fecha_ultimaReserva) {
        super(id_usuario);
        setComensales(comensales);
        setFecha_ultimaReserva(fecha_ultimaReserva);
    }

    public Cliente() {
    }

    //GETTERS I SETTERS
    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }
    public Date getFecha_ultimaReserva() {
        return fecha_ultimaReserva;
    }

    public void setFecha_ultimaReserva(Date fecha_ultimaReserva) {
        this.fecha_ultimaReserva = fecha_ultimaReserva;
    }

}
