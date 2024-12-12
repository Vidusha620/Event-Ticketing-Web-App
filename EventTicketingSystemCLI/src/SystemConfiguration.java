public class SystemConfiguration {

    //Initializing attributes
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    //Getters and Setters

    public int getTotalTickets(){
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets){
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate){
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate(){
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate){
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity(){
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity){
        this.maxTicketCapacity = maxTicketCapacity;
    }

    @Override
    public String toString(){
        return "System Configuration {" +
                "Total Number of Tickets = "+ totalTickets +
                ", Ticket Release Rate = "+ ticketReleaseRate +
                ", tickets/min, Customer Retrieval Rate = " + customerRetrievalRate +
                " customers/min, Maximum Ticket Capacity = " + maxTicketCapacity + '}';
    }

}