public class Customer implements Runnable {
    private TicketPool ticketPool; //One ticket pool used by all Vendors and Customers
    private int purchasingNoOfTicket; //Tickets to be bought by Customer

    private int customerRetrievalRate; //Rate of tickets removing from the pool

    //Constructor
    public Customer(TicketPool ticketPool, int purchasingNoOfTicket, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.purchasingNoOfTicket = purchasingNoOfTicket;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        for (int i = 0; i < purchasingNoOfTicket; i++) {
            Ticket ticket = ticketPool.buyTicket();
            //System.out.println("Ticket: "+ ticket + "bought by: "+ Thread.currentThread().getName());

            try {
                Thread.sleep(customerRetrievalRate);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
