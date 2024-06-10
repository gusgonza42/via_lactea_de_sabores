package abp.project.mesapp.mongo;

import abp.project.mesapp.database.DataBaseConnectionMongo;
import abp.project.mesapp.model.Producto;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;
import com.mongodb.client.MongoClient;

import java.util.Date;

public class Mongo {
    public static void main(String[] args) throws CheckError {
        DataBaseConnectionMongo dbMongo = new DataBaseConnectionMongo();
        System.out.println("Conexión establecida");

        MongoClient mongoClient = dbMongo.connectDB(Constantes.uri);

        try {
            Tickets ticket = new Tickets(1, 1, 1, new Date(), 1);
            ticket.saveTicket(mongoClient, "vialacteadesabores", "tickets");

            Proveedores proveedor = new Proveedores(1, "Proveedor A", "Calle 123", "123456789", "proveedor@example.com");
            Producto producto1 = new Producto(1, "Producto 1", 10, "Proveedor A");
            Producto producto2 = new Producto(2, "Producto 2", 20, "Proveedor A");
            //proveedor.getProductos().add(producto1);
            //proveedor.getProductos().add(producto2);
            //Proveedores.saveProveedorToMongoDB(mongoClient, "vialacteadesabores", "proveedores", proveedor);

            System.out.println("Operaciones de base de datos realizadas con éxito");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        dbMongo.disconnectDB(mongoClient);
        System.out.println("Disconnected");

    }
}