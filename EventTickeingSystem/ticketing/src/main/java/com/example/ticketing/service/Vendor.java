package com.example.ticketing.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int totalTickets;
    private final int releaseRate;
    private final AtomicInteger ticketCounter;
    private final List<String> logs;

    public Vendor(TicketPool ticketPool, int totalTickets, int releaseRate, AtomicInteger ticketCounter, List<String> logs) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.releaseRate = releaseRate;
        this.ticketCounter = ticketCounter;
        this.logs = logs;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int ticketId = ticketCounter.incrementAndGet();
                if (ticketId > totalTickets) {
                    break; // Stop when all tickets are released
                }
                // Add the ticket to the pool with the specific ID
                ticketPool.addTicket(ticketId);
                synchronized (ticketPool) {
                    synchronized (logs) {
                        logs.add("Ticket added Ticket ID: " + ticketId);
                    }
                }
                Thread.sleep(1000 / releaseRate); // Sleep based on release rate
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Vendor thread interrupted: " + e.getMessage());
        }
    }
}
