package com.example.ticketing.handler;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.stereotype.Component;

@Component
public class TicketWebSocketHandler extends TextWebSocketHandler {

    /**
     * Handles incoming WebSocket messages.
     * In this case, when the client sends a message, a predefined response is sent back.
     *
     * @param session the WebSocket session
     * @param message the incoming message from the client
     * @throws Exception if an error occurs while sending the message
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Here, we are sending a predefined message to the client.
        // You can modify this to send dynamic data based on your logic.
        String response = "Ticket added - Ticket ID: 1 - current size is - 1";
        session.sendMessage(new TextMessage(response));

        // Optionally, handle incoming messages from the client if needed:
        System.out.println("Received message: " + message.getPayload());
    }
}
