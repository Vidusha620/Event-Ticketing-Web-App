import java.util.Scanner;

public class TicketingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SystemConfiguration systemConfiguration = new SystemConfiguration();

        System.out.println("Welcome!");

        //Total Number of Tickets
        System.out.println("Enter the Total number of tickets: ");
        systemConfiguration.setTotalTickets(validateInput(scanner));

        //Ticket Release Rate
        System.out.println("Enter the Ticket Release rate(tickets per second): ");
        systemConfiguration.setTicketReleaseRate(validateInput(scanner));

        //Customer Retrieval Rate
        System.out.println("Enter the Customer Retrieval rate(customers per second): ");
        systemConfiguration.setCustomerRetrievalRate(validateInput(scanner));

        //Maximum Ticket Capacity
        System.out.println("Enter the Maximum Ticket Capacity: ");
        systemConfiguration.setMaxTicketCapacity(validateInput(scanner));

        System.out.println("Configuration is complete.");

        //Save configurations to a file
        System.out.println("Enter the file name to be saved as: ");
        String fileName = scanner.next();
        FileManager.saveAsJSON(systemConfiguration,fileName);



        //Starting the system
        start(systemConfiguration);

        scanner.close();

    }

    //Validating user Inputs in configuration
    private static int validateInput(Scanner scanner) {
        int input = -1;
        boolean valid = false;

        while (!valid) {
            if(scanner.hasNextInt()){
                input = scanner.nextInt();
                if (input > 0){ //Input value has to be a positive integer
                    valid = true;
                }
                else {
                    System.out.println("Please enter a positive value: ");
                }
            }
            else {
                System.out.println("Please enter a valid value: ");
                scanner.next();
            }

        }
        return input;
    }

    private static void start(SystemConfiguration systemConfiguration){
        System.out.println("\nStarting the system with the following configuration...");
        System.out.println(systemConfiguration);

        //Starting the ticketing system
        Main.start(systemConfiguration);

    }
}