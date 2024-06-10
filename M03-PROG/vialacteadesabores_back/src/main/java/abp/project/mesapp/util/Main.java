package abp.project.mesapp.util;

import abp.project.mesapp.database.DataBaseConnection;
import abp.project.mesapp.database.DataBaseConnectionMongo;
import abp.project.mesapp.model.Producto;
import abp.project.mesapp.mongo.Proveedores;
import abp.project.mesapp.mongo.Tickets;
import com.mongodb.client.MongoClient;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws CheckError {
        DataBaseConnection db = new DataBaseConnection();
        db.init();
        db.desconectar();
    }
}

