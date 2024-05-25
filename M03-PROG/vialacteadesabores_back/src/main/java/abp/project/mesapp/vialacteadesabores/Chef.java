package abp.project.mesapp.vialacteadesabores;

import java.sql.Date;

public class Chef extends Usuario{
    //ATRIBUTOS DE LA CLASE CHEF
    private double salario;
    private Date fechaContratacion;
    private boolean disponible;

    //CONSTRUCTOR


    public Chef(int id_usuario ,double salario, Date fechaContratacion, boolean disponible) {
        super(id_usuario);
        setSalario(salario);
        setFechaContratacion(fechaContratacion);
        setDisponible(disponible);
    }

    //GETTERS Y SETTERS
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
