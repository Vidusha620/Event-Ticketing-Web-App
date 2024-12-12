import java.math.BigDecimal;

public class Vendor implements Runnable {
    private TicketPool ticketPool; //One ticket pool used by all Vendors and Customers
    private int totalNoOfTicket; //Tickets to be sold by Vendor

    private int ticketReleaseRate; //Rate of tickets adding to the pool

    //Constructor
    public Vendor( TicketPool ticketPool, int totalNoOfTicket, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalNoOfTicket = totalNoOfTicket;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        for (int i = 1; i < totalNoOfTicket; i++) {
            Ticket ticket = new Ticket(i, "Sample Event", new BigDecimal(2500));
            ticketPool.addTicket(ticket);

            try {
                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
