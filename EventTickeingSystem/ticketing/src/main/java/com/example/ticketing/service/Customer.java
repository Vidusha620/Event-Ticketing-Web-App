package com.example.ticketing.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalRate;
    private final AtomicInteger ticketsConsumed;
    private final int totalTickets;
    private final List<String> logs;

    public Customer(TicketPool ticketPool, int retrievalRate, AtomicInteger ticketsConsumed, int totalTickets, List<String> logs) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
        this.ticketsConsumed = ticketsConsumed;
        this.totalTickets = totalTickets;
        this.logs = logs;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (ticketsConsumed.incrementAndGet() > totalTickets) {
                    break; // Stop when all tickets are consumed
                }
                Integer ticketId = ticketPool.retrieveTicket();
                synchronized (ticketPool) {
                    synchronized (logs) {
                        logs.add("Ticket bought - Ticket ID: " + ticketId + " - currently " + ticketPool.getSize() + " tickets are available.");
                    }
                }
                Thread.sleep(retrievalRate * 1000L); // Delay based on customerRetrievalRate
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
