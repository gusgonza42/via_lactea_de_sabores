package abp.project.mesapp.mongo;

import abp.project.mesapp.database.DataBaseConnectionMongo;
import abp.project.mesapp.model.Producto;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoCollection;

import java.util.List;

public class ProveedoresDao {
    //INSERTAR PROVEEDOR
    public void newProveedor(Proveedores proveedor) {

        //insertamos un proveedor nuevo
        try {
            DataBaseConnectionMongo dbMongo = new DataBaseConnectionMongo();
            //CONECTAMOS
            System.out.println("Conexión establecida");
            MongoClient mongoClient = dbMongo.connectDB(Constantes.uri);
            //NOS METEMOS A LA BBDD
            mongoClient.getDatabase("vialacteadesabores");
            //CREAMOS LA COLECCION
            MongoCollection<Document> newCollection = mongoClient.getDatabase("vialacteadesabores").getCollection("proveedores");
            Document proveedorDocument = new Document();
            proveedorDocument.append("id", proveedor.getId())
                    .append("nombre", proveedor.getNombre())
                    .append("direccion", proveedor.getDireccion())
                    .append("telefono", proveedor.getTelefono())
                    .append("email", proveedor.getEmail());
            newCollection.insertOne(proveedorDocument);
        } catch (CheckError e) {
            throw new RuntimeException(e);
        }
    }
        //BORRAR PROVEEDOR
    public void deleteProveedor(Proveedores proveedor) {
        try{
            DataBaseConnectionMongo dbMongo = new DataBaseConnectionMongo();
            MongoClient mongoClient = dbMongo.connectDB(Constantes.uri);
            mongoClient.getDatabase("vialacteadesabores");
            MongoCollection<Document> newCollection = mongoClient.getDatabase("vialacteadesabores").getCollection("proveedores");
            Document proveedorDocument = new Document();
            proveedorDocument.append("id", proveedor.getId())
                    .append("nombre", proveedor.getNombre())
                    .append("direccion", proveedor.getDireccion())
                    .append("telefono", proveedor.getTelefono())
                    .append("email", proveedor.getEmail());
            newCollection.deleteOne(proveedorDocument);
        } catch (CheckError e) {
            throw new RuntimeException(e);

        }
    }
    //MODIFICAR PROVEEDOR
    public void modifyProveedor(Proveedores proveedor) {
        try {
            DataBaseConnectionMongo dbMongo = new DataBaseConnectionMongo();
            MongoClient mongoClient = dbMongo.connectDB(Constantes.uri);
            mongoClient.getDatabase("vialacteadesabores");
            MongoCollection<Document> newCollection = mongoClient.getDatabase("vialacteadesabores").getCollection("proveedores");
            Document proveedorDocument = new Document();
            proveedorDocument.append("id", proveedor.getId())
                    .append("nombre", proveedor.getNombre())
                    .append("direccion", proveedor.getDireccion())
                    .append("telefono", proveedor.getTelefono())
                    .append("email", proveedor.getEmail());
            newCollection.replaceOne(new Document("id", proveedor.getId()), proveedorDocument);

        } catch (CheckError e) {
            throw new RuntimeException(e);

        }
    }
    //Crear PROVEEDORES
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
