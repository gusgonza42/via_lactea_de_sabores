package abp.project.mesapp.mongo;

import abp.project.mesapp.database.DataBaseConnectionMongo;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static abp.project.mesapp.util.CheckError.*;

public class TicketsDao {
    //INSERTAR
    public void newTicket(Tickets ticket) throws MongoException, CheckError {
        //INSTANCIAMOS MONGOCLASE
        try {
            DataBaseConnectionMongo dbMongo = new DataBaseConnectionMongo();
            //CONECTAMOS
            System.out.println("Conexión establecida");
            MongoClient mongoClient = dbMongo.connectDB(Constantes.uri);
            //NOS METEMOS A LA BBDD
            mongoClient.getDatabase("vialacteadesabores");
            //CREAMOS LA COLECCION
            MongoCollection<Document> newCollection = mongoClient.getDatabase("vialacteadesabores").getCollection("tickets");
            Document ticketDocument = new Document();
            ticketDocument.append("Order", ticket.getOrder())
                    .append("table", ticket.getTable())
                    .append("waiter", ticket.getWaiter())
                    .append("date", ticket.getDate())
                    .append("num_diners",ticket.getNum_diners());

            newCollection.insertOne(ticketDocument);
        } catch (MongoException e) {
            throw new CheckError(ERROR_CREATE_MONGOTICKET);

        }
    }

    //BORRAR
    //TIPO POST
    //DEVUELVE MENSAJE DE QUE SE HA BORRADO, SE MUESTRA POR SERVIDOR QUE SE HAN BORRADO LOS DATOS
    public void deleteTicket(Tickets ticket) throws MongoException, CheckError {
        try {
            DataBaseConnectionMongo dbMongo = new DataBaseConnectionMongo();
            MongoClient mongoClient = dbMongo.connectDB(Constantes.uri);
            mongoClient.getDatabase("vialacteadesabores");
            MongoCollection<Document> newCollection = mongoClient.getDatabase("vialacteadesabores").getCollection("tickets");
            Document ticketDocument = new Document();
            ticketDocument.append("Order", ticket.getOrder())
                    .append("table", ticket.getTable())
                    .append("waiter", ticket.getWaiter())
                    .append("date", ticket.getDate())
                    .append("num_diners",ticket.getNum_diners());

            newCollection.deleteOne(ticketDocument);
        } catch (MongoException e) {
            throw new CheckError(ERROR_CREATE_MONGOTICKET);
        }
    }

    //BUSCAR MOSTRAR TODOS LOS TICKETS
    //Es el unico que devuelve el el objeto donde en el front se tratan los datos, es de tipo post
    public Tickets showAllTickets() throws MongoException, CheckError {
        try {
            DataBaseConnectionMongo dbMongo = new DataBaseConnectionMongo();
            MongoClient mongoClient = dbMongo.connectDB(Constantes.uri);
            mongoClient.getDatabase("vialacteadesabores");
            MongoCollection<Document> newCollection = mongoClient.getDatabase("vialacteadesabores").getCollection("tickets");
            Document ticketDocument = new Document();
            Tickets ticket = new Tickets();
            for (Document doc : newCollection.find()) {
                ticketDocument.append("Order", doc.getInteger("Order"))
                        .append("table", doc.getInteger("table"))
                        .append("waiter", doc.getInteger("waiter"))
                        .append("date", doc.getDate("date"))
                        .append("num_diners", doc.getInteger("num_diners"));
            }
            return ticket;
        } catch (MongoException e) {
            throw new CheckError(ERROR_CREATE_MONGOTICKET);
        }

    }

    //TIPO UPDATE
    //ACTUALIZAR TICKET
    public void updateTicket(Tickets ticket) throws MongoException, CheckError {
        try {
            DataBaseConnectionMongo dbMongo = new DataBaseConnectionMongo();
            MongoClient mongoClient = dbMongo.connectDB(Constantes.uri);
            mongoClient.getDatabase("vialacteadesabores");
            MongoCollection<Document> newCollection = mongoClient.getDatabase("vialacteadesabores").getCollection("tickets");
            Document ticketDocument = new Document();
            ticketDocument.append("Order", ticket.getOrder())
                    .append("table", ticket.getTable())
                    .append("waiter", ticket.getWaiter())
                    .append("date", ticket.getDate())
                    .append("num_diners", ticket.getNum_diners());

            newCollection.replaceOne(ticketDocument, ticketDocument);
        } catch (MongoException e) {
            throw new CheckError(ERROR_CREATE_MONGOTICKET);
        }
    }
}
