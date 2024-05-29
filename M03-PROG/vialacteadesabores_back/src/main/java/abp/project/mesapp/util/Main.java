package abp.project.mesapp.util;

import abp.project.mesapp.database.DataBaseConnection;
import abp.project.mesapp.database.DataBaseConnectionMongo;
import abp.project.mesapp.model.MenuFunciones;
import abp.project.mesapp.model.Producto;
import abp.project.mesapp.model.Proveedores;
import abp.project.mesapp.model.Tickets;
import com.mongodb.client.MongoClient;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws CheckError {
        try{
            DataBaseConnection db = new DataBaseConnection();
            db.init();
            DataBaseConnectionMongo dbMongo = new DataBaseConnectionMongo();
            System.out.println("Conexión establecida");
            MongoClient mongoClient = dbMongo.connectDB(Constantes.uri);
            System.out.println("Disconnected");
            dbMongo.disconnectDB(mongoClient);
            Tickets ticket = new Tickets(1, 1, 1, new Date(), 1);
            ticket.saveTicket(mongoClient, "vialacteadesabores", "tickets");
            Proveedores proveedor = new Proveedores(1, "Proveedor A", "Calle 123", "123456789", "proveedor@example.com");
            Producto producto1 = new Producto(1, "Producto 1", 10, "Proveedor A");
            Producto producto2 = new Producto(2, "Producto 2", 20, "Proveedor A");
            proveedor.getProductos().add(producto1);
            proveedor.getProductos().add(producto2);
            Proveedores.saveProveedorToMongoDB(mongoClient, "vialacteadesabores", "proveedores", proveedor);
            db.desconectar();
        }catch (SQLException e){
            throw new CheckError(CheckError.ERROR_CONNECT_BBDD);
        }
    }
}

