package org.ilernabarcelona.vialacteadesabores;

import java.sql.Date;

public class Camarero extends Usuario{
    //ATRIBUTOS
    private double salario;
    private Date fecha_contratacion;
    private boolean disponibilidad;

    //CONSTRUCTOR

    public Camarero(int id_usuario, double salario, Date fecha_contratacion, boolean disponibilidad) {
        super(id_usuario);
        setSalario(salario);
        setFecha_contratacion(fecha_contratacion);
        setDisponibilidad(disponibilidad);
    }


    //GETTERS Y SETTERS

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
