/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customersupport.gui;

/**
 *
 * @author yani_
 */
import com.customersupport.auth.JwtUtil;
import com.customersupport.chatbot.ChatbotProto;
import com.customersupport.chatbot.ChatbotServiceGrpc;
import com.customersupport.ticketing.TicketingProto;
import com.customersupport.ticketing.TicketingServiceGrpc;
import com.customersupport.sentiment.SentimentProto;
import com.customersupport.sentiment.SentimentServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class SupportGUI extends JFrame {

    private JTextField userIdField, messageField, ticketIdField, multiTicketIdsField;
    private JTextArea consoleArea;
    private JButton sendButton, statusButton;

    private ChatbotServiceGrpc.ChatbotServiceBlockingStub chatbotStub;
    private TicketingServiceGrpc.TicketingServiceBlockingStub ticketingBlockingStub;
    private TicketingServiceGrpc.TicketingServiceStub ticketingAsyncStub;
    private SentimentServiceGrpc.SentimentServiceStub sentimentStub;
    private StreamObserver<SentimentProto.SentimentRequest> sentimentStream;

    public SupportGUI() {
        // window
        setTitle("Support GUI");
        setSize(550, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // gRPC Channels
        ManagedChannel chatbotChannel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
        chatbotStub = ChatbotServiceGrpc.newBlockingStub(chatbotChannel);

        ManagedChannel ticketingChannel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();
        ticketingBlockingStub = TicketingServiceGrpc.newBlockingStub(ticketingChannel);
        ticketingAsyncStub = TicketingServiceGrpc.newStub(ticketingChannel);


        ManagedChannel sentimentChannel = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();
        sentimentStub = SentimentServiceGrpc.newStub(sentimentChannel);
        startSentimentStream();

        // Panel superior
        JPanel topPanel = new JPanel(new GridLayout(4, 2));
        userIdField = new JTextField();
        messageField = new JTextField();
        ticketIdField = new JTextField();
        multiTicketIdsField = new JTextField();

        topPanel.add(new JLabel("User ID:"));
        topPanel.add(userIdField);
        topPanel.add(new JLabel("Message:"));
        topPanel.add(messageField);
        topPanel.add(new JLabel("Ticket ID:"));
        topPanel.add(ticketIdField);
        topPanel.add(new JLabel("IDs múltiples ',':"));
        topPanel.add(multiTicketIdsField);

        add(topPanel, BorderLayout.NORTH);

        // Console
        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        add(new JScrollPane(consoleArea), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        sendButton = new JButton("Send to Chatbot");
        statusButton = new JButton("Check Ticket Status");
        JButton multiCheckButton = new JButton("Check Multiple Tickets");
        JButton closeTicketButton = new JButton("closed ticket");

        buttonPanel.add(sendButton);
        buttonPanel.add(statusButton);
        buttonPanel.add(multiCheckButton);
        buttonPanel.add(closeTicketButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Acciones
        sendButton.addActionListener(e -> sendMessageToChatbot());
        statusButton.addActionListener(e -> checkTicketStatus());
        multiCheckButton.addActionListener(e -> checkMultipleTicketStatuses());
        closeTicketButton.addActionListener(e -> closeTicket());
        setVisible(true);
    }

    private void sendMessageToChatbot() {
        String userId = userIdField.getText().trim();
        String message = messageField.getText().trim();

        if (userId.isEmpty() || message.isEmpty()) {
            consoleArea.append("⚠️ Please enter User ID and a message.\n");
            return;
        }

        String token = JwtUtil.generateToken(userId);

        // Sentiment service
        SentimentProto.SentimentRequest sentimentRequest = SentimentProto.SentimentRequest.newBuilder()
                .setUserId(userId)
                .setPhrase(message)
                .setToken(token)
                .build();
        sentimentStream.onNext(sentimentRequest);

        // chatbot
        ChatbotProto.ChatRequest request = ChatbotProto.ChatRequest.newBuilder()
                .setUserId(userId)
                .setMessage(message)
                .setToken(token)
                .build();

        try {
            ChatbotProto.ChatResponse response = chatbotStub.sendMessage(request);
            consoleArea.append("🗨️ Chatbot: " + response.getReply() + "\n");

            if (response.getEscalate()) {
                String[] parts = response.getReply().split(": ");
                if (parts.length > 1) {
                    String ticketId = parts[1].trim();
                    ticketIdField.setText(ticketId);
                    consoleArea.append("Ticket generated: " + ticketId + "\n");
                } else {
                    consoleArea.append("⚠️ Escalation requested but no ticket ID found.\n");
                }
            }

        } catch (Exception ex) {
            consoleArea.append("❌ Chatbot error: " + ex.getMessage() + "\n");
        }
    }

    private void checkTicketStatus() {
        String userId = userIdField.getText().trim();
        String ticketId = ticketIdField.getText().trim();

        if (userId.isEmpty() || ticketId.isEmpty()) {
            consoleArea.append("⚠️ Provide User ID and Ticket ID\n");
            return;
        }

        String token = JwtUtil.generateToken(userId);

        TicketingProto.TicketStatusRequest request = TicketingProto.TicketStatusRequest.newBuilder()
                .setTicketId(ticketId)
                .setToken(token)
                .build();

        try {
            TicketingProto.TicketStatusResponse response = ticketingBlockingStub.getTicketStatus(request);
            consoleArea.append("Ticket Status: " + response.getStatus() + "\n");

        } catch (Exception ex) {
            consoleArea.append("❌ Error checking status: " + ex.getMessage() + "\n");
        }
    }
    private void closeTicket() {
        String userId = userIdField.getText().trim();
        String ticketId = ticketIdField.getText().trim();

        if (userId.isEmpty() || ticketId.isEmpty()) {
            consoleArea.append("⚠️ Provide User ID and Ticket ID  for closing\n");
            return;
        }

        String token = JwtUtil.generateToken(userId);

        TicketingProto.TicketCloseRequest request = TicketingProto.TicketCloseRequest.newBuilder()
                .setTicketId(ticketId)
                .setToken(token)
                .build();

        try {
            TicketingProto.TicketCloseResponse response = ticketingBlockingStub.closeTicket(request);
            consoleArea.append("Warning" + response.getMessage() + "\n");

        } catch (Exception ex) {
            consoleArea.append("❌ Error al cerrar ticket: " + ex.getMessage() + "\n");
        }
    }

    private void checkMultipleTicketStatuses() {
        String userId = userIdField.getText().trim();
        String rawIds = multiTicketIdsField.getText().trim();

        if (userId.isEmpty() || rawIds.isEmpty()) {
            consoleArea.append("⚠️ Provide User ID and at least one Ticket ID\n");
            return;
        }

        String token = JwtUtil.generateToken(userId);
        String[] ticketIds = rawIds.split(",");

        StreamObserver<TicketingProto.TicketStatusBatchResponse> responseObserver =
                new StreamObserver<TicketingProto.TicketStatusBatchResponse>() {
                    @Override
                    public void onNext(TicketingProto.TicketStatusBatchResponse response) {
                        SwingUtilities.invokeLater(() -> {
                            consoleArea.append("Ticket Status Results:\n");
                            for (String status : response.getStatusesList()) {
                                consoleArea.append("   • " + status + "\n");
                            }
                            logMultipleStatus(userId, ticketIds, response.getStatusesList());
                        });
                    }

                    @Override
                    public void onError(Throwable t) {
                        SwingUtilities.invokeLater(() ->
                                consoleArea.append("❌ Error in batch check: " + t.getMessage() + "\n"));
                    }

                    @Override
                    public void onCompleted() {
                        consoleArea.append(" Batch status check completed.\n");
                    }
                };

        StreamObserver<TicketingProto.TicketStatusRequest> requestObserver =
                ticketingAsyncStub.checkMultipleStatuses(responseObserver);

        for (String id : ticketIds) {
            String trimmed = id.trim();
            if (!trimmed.isEmpty()) {
                TicketingProto.TicketStatusRequest request = TicketingProto.TicketStatusRequest.newBuilder()
                        .setTicketId(trimmed)
                        .setToken(token)
                        .build();
                requestObserver.onNext(request);
            }
        }

        requestObserver.onCompleted();
    }

    private void startSentimentStream() {
        sentimentStream = sentimentStub.analyzeSentiments(new StreamObserver<SentimentProto.SentimentResponse>() {
            @Override
            public void onNext(SentimentProto.SentimentResponse response) {
                SwingUtilities.invokeLater(() -> {
                    consoleArea.append("Feeling: " +
                            response.getSentiment() + " (" + response.getPhrase() + ")\n");
                });
            }

            @Override
            public void onError(Throwable t) {
                consoleArea.append("❌ Sentiment analysis error: " + t.getMessage() + "\n");
            }

            @Override
            public void onCompleted() {
                consoleArea.append("Sentiment analysis completed\n");
            }
        });
    }

    private void logMultipleStatus(String userId, String[] ids, java.util.List<String> statuses) {
        try (FileWriter writer = new FileWriter("multi_ticket_logs.txt", true)) {
            writer.write("=== Multiple checking - " + LocalDateTime.now() + " ===\n");
            writer.write("User ID: " + userId + "\n");
            writer.write("Tickets:\n");
            for (String id : ids) {
                writer.write(" - " + id.trim() + "\n");
            }
            writer.write("Results:\n");
            for (String status : statuses) {
                writer.write("   • " + status + "\n");
            }
            writer.write("========================================\n\n");
        } catch (IOException e) {
            consoleArea.append("❌ Error writing log: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new SupportGUI();
    }
}
