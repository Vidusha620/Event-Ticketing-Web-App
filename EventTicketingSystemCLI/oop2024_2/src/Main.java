public class Main {
    public static void start(SystemConfiguration systemConfiguration) {
        //Use configured values
        TicketPool ticketPool = new TicketPool(systemConfiguration.getMaxTicketCapacity());

        Vendor[] vendors = new Vendor[systemConfiguration.getTicketReleaseRate()]; //Vendors' array
        for (int i = 1; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool, systemConfiguration.getTotalTickets(), systemConfiguration.getTicketReleaseRate());
            Thread vendorThread = new Thread(vendors[i],"Vendor Id:" + i);
            vendorThread.start();
        }

        Customer[] customers = new Customer[systemConfiguration.getCustomerRetrievalRate()]; //Customers' array
        for (int i = 1; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, systemConfiguration.getCustomerRetrievalRate(), systemConfiguration.getTicketReleaseRate());
            Thread customerThread = new Thread(customers[i],"Customer Id:" + i);
            customerThread.start();
        }
    }
}
