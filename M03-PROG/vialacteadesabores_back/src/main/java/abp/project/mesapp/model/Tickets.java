package abp.project.mesapp.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import lombok.Getter;
import org.bson.Document;

import java.util.Date;


public class Tickets {
    int order;
    int table;
    int waiter;
    Date date;
    int num_diners;

    public Tickets(int order, int table, int waiter, Date date, int num_diners) {
        setOrder(order);
        setTable(table);
        setWaiter(waiter);
        setDate(date);
        setNumDinners(num_diners);
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public void setWaiter(int waiter) {
        this.waiter = waiter;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNumDinners(int num_diners) {
        this.num_diners = num_diners;
    }

    public String toString() {
        return "Order: " + order + ", Table: " + table + ", Waiter: " + waiter + ", Date: " + date + ", Number of diners: " + num_diners;
    }

    public  void saveTicket(MongoClient mongoClient, String databaseName, String collectionName) {
        MongoCollection<Document> collection = mongoClient.getDatabase(databaseName).getCollection(collectionName);

        Document ticketDocument = new Document();
        ticketDocument.append("order", this.order)
                .append("table", this.table)
                .append("waiter", this.waiter)
                .append("date", this.date)
                .append("num_diners", this.num_diners);

        collection.insertOne(ticketDocument);
    }
}