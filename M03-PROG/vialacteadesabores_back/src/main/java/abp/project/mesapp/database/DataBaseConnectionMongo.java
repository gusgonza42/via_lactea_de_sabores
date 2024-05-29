package abp.project.mesapp.database;

import abp.project.mesapp.util.CheckError;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnectionMongo {
    public MongoClient connectDB(String uri) throws CheckError{
        // Reduce logging level from MongoDB driver
        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
        MongoClient mongoClient = null;
        try {
            mongoClient = MongoClients.create(uri);
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println("Error connecting");
            throw new CheckError(CheckError.ERROR_CONNECT_BBDD);
        }
        return mongoClient;
    }

    public void disconnectDB(MongoClient mongoClient) {
        System.out.println("Disconnected");
        mongoClient.close();
    }
}
