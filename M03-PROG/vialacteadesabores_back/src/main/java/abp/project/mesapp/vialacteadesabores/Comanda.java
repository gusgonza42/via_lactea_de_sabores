package abp.project.mesapp.vialacteadesabores;

import java.sql.Date;

public class Comanda {
    //ATRIBUTOS
    private int id_comanda;
    private Date fecha;
    private boolean pagado;
    private int num_mesa;

    //CONSTRUCTOR

    public Comanda(int id_comanda, Date fecha, boolean pagado, int num_mesa) {
        setId_comanda(id_comanda);
        setFecha(fecha);
        setPagado(pagado);
        setNum_mesa(num_mesa);
    }


    //GETTERS Y SETTERS

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(int num_mesa) {
        this.num_mesa = num_mesa;
    }
}
