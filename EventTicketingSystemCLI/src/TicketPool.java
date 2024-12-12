import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {

    private int maxTicketCapacity;
    private Queue<Ticket> queue;

    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.queue = new LinkedList<>();
    }

    //Add ticket method will be executed by Vendor

    public synchronized void addTicket(Ticket ticket) {
        while(queue.size() >= maxTicketCapacity){
            try {
                wait(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

            this.queue.add(ticket); //Adding ticket
            notifyAll(); //Notifying all the waiting threads
            System.out.println("A ticket was added by: " + Thread.currentThread().getName() + ", current size of the queue is: " + queue.size()); //Informing who added the ticket to the queue and the queue length at the time

    }

    //Buy ticket method will be executed by Customer

    public synchronized Ticket buyTicket() {
        while (queue.isEmpty()) {
            try {
                wait(1000); //Making threads wait when queue is empty
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        if (!queue.isEmpty()){
            Ticket ticket = queue.poll();
            notifyAll(); //Notifying all the waiting threads
            System.out.println(Thread.currentThread().getName() + " bought a ticket, current size of the queue is: " + queue.size()); //Informing who bought the ticket and the queue length at the moment
            return ticket;
        }
        return null;
    }
}
