package abp.project.mesapp.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class Proveedores {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private List<Producto> productos;


    public Proveedores(int id, String nombre, String direccion, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Proveedores{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public static void saveProveedorToMongoDB(MongoClient mongoClient, String databaseName, String collectionName, Proveedores proveedor) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        Document proveedorDocument = new Document();
        proveedorDocument.append("id", proveedor.getId())
                .append("nombre", proveedor.getNombre())
                .append("direccion", proveedor.getDireccion())
                .append("telefono", proveedor.getTelefono())
                .append("email", proveedor.getEmail());

        // Convertir lista de productos a documentos BSON
        List<Producto> productos = proveedor.getProductos();
        for (Producto producto : productos) {
            Document productoDocument = new Document();
            productoDocument.append("id_producto", producto.getIdproducto())
                    .append("nombre", producto.getNombre())
                    .append("stock", producto.getStock())
                    .append("proveedor", proveedor.getNombre()); // El nombre del proveedor se asocia con cada producto

            proveedorDocument.append("productos", productoDocument);
        }

        // Insertar documento del proveedor en la colección
        collection.insertOne(proveedorDocument);
    }


}
