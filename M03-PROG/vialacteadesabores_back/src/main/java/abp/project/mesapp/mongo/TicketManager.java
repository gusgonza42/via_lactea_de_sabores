package abp.project.mesapp.mongo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketManager {
    private final MongoCollection<Document> collection;

    public TicketManager(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void generarNuevoTicket(Tickets ticket) {
        Document ticketDoc = new Document("order", ticket.getOrder())
                .append("table", ticket.getTable())
                .append("waiter", ticket.getWaiter())
                .append("date", ticket.getDate())
                .append("num_diners", ticket.getNum_diners());

        collection.insertOne(ticketDoc);
        System.out.println("Ticket generado y guardado correctamente.");
    }

    public List<Tickets> mostrarTodosLosTickets() {
        List<Tickets> todosLosTickets = new ArrayList<>();
        for (Document ticketDoc : collection.find()) {
            Tickets ticket = new Tickets(
                    ticketDoc.getInteger("order"),
                    ticketDoc.getInteger("table"),
                    ticketDoc.getInteger("waiter"),
                    (Date) ticketDoc.get("date"),
                    ticketDoc.getInteger("num_diners")
            );
            todosLosTickets.add(ticket);
            imprimirTicket(ticket);
        }
        if (todosLosTickets.isEmpty()) {
            System.out.println("No hay tickets guardados.");
        }
        return todosLosTickets;
    }

    public void eliminarTicket(int order) {
        collection.deleteOne(new Document("order", order));
        System.out.println("Ticket eliminado correctamente.");
    }

    public void actualizarTicket(int order, int nuevosNumDiners) {
        Document ticketDoc = collection.find(new Document("order", order)).first();

        if (ticketDoc != null) {
            ticketDoc.put("num_diners", nuevosNumDiners);
            collection.replaceOne(new Document("order", order), ticketDoc);
            System.out.println("Ticket actualizado correctamente.");
        } else {
            System.out.println("No se encontró un ticket con el número de orden proporcionado.");
        }
    }

    private void imprimirTicket(Tickets ticket) {
        System.out.println("=============================================");
        System.out.println("Order: " + ticket.getOrder());
        System.out.println("Table: " + ticket.getTable());
        System.out.println("Waiter: " + ticket.getWaiter());
        System.out.println("Date: " + ticket.getDate());
        System.out.println("Number of Diners: " + ticket.getNum_diners());
        System.out.println("=============================================");
        System.out.println();
    }
}
