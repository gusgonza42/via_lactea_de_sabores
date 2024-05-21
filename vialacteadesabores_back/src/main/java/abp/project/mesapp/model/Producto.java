package abp.project.mesapp.model;

public class Producto {
    private int id_producto;
    private String nombre;
    private int stock;
    private String proveedor;

    public Producto(int id_producto, String nombre, int stock, String proveedor) {
        setIdproducto(id_producto);
        setNombre(nombre);
        setStock(stock);
        setProveedor(proveedor);
    }

    public int getIdproducto() {
        return id_producto;
    }

    public void setIdproducto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }


}
