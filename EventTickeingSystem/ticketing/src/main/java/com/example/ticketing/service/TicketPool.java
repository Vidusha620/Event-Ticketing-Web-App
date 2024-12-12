package com.example.ticketing.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class TicketPool {
    private final Queue<Integer> tickets = new LinkedList<>();
    private int capacity;
    private int ticketCounter = 1;  // Ensuring sequential ticket IDs

    // Adds a ticket to the pool and ensures it follows the order
    public synchronized void addTicket(int ticketId) throws InterruptedException {
        while (tickets.size() >= capacity) {
            wait(); // Wait if the pool is full
        }

        // Add the specified ticket to the pool
        tickets.add(ticketId);
        System.out.println("Ticket Ticket ID: " + ticketId + " added to the system");  // Log ticket addition

        notifyAll();  // Notify customers that tickets are available
    }

    // Retrieves a ticket from the pool (FIFO)
    public synchronized Integer retrieveTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            wait(); // Wait if there are no tickets
        }

        // Consume a ticket (FIFO)
        Integer ticket = tickets.poll();
        System.out.println("Ticket Ticket ID:" + ticket + " bought.");  // Log ticket purchase
        notifyAll();  // Notify vendors that space is available for more tickets
        return ticket;
    }

    public synchronized int getSize() {
        return tickets.size(); // Get current size of ticket pool
    }

    public synchronized void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
