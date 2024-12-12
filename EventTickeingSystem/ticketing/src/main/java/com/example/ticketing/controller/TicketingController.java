package com.example.ticketing.controller;

import com.example.ticketing.model.SystemConfig;
import com.example.ticketing.service.Customer;
import com.example.ticketing.service.TicketPool;
import com.example.ticketing.service.Vendor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React frontend
@RestController
@RequestMapping("/api")
public class TicketingController {
    private final TicketPool ticketPool = new TicketPool();
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final AtomicInteger ticketCounter = new AtomicInteger(0); // Unique ticket IDs
    private final AtomicInteger ticketsConsumed = new AtomicInteger(0); // Track tickets consumed
    private final List<String> logs = new ArrayList<>(); // Shared log list for frontend

    @PostMapping("/configure")
    public String configureSystem(@RequestBody SystemConfig config) {
        ticketCounter.set(0); // Reset ticket counter
        ticketsConsumed.set(0); // Reset consumed counter
        logs.clear(); // Clear previous logs

        ticketPool.setCapacity(config.getMaxTicketCapacity()); // Set capacity for the ticket pool

        // Start Vendors
        for (int i = 0; i < 1; i++) { // Simulate multiple vendors
            executorService.execute(new Vendor(ticketPool, config.getTotalTickets(), config.getTicketReleaseRate(), ticketCounter, logs));
        }

        // Start Customers
        for (int i = 0; i < 1; i++) { // Simulate multiple customers
            executorService.execute(new Customer(ticketPool, config.getCustomerRetrievalRate(), ticketsConsumed, config.getTotalTickets(), logs));
        }

        return "System configured and running!";
    }

    @GetMapping("/logs")
    public List<String> getLogs() {
        return logs;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Ticketing system is running.";
    }
}
