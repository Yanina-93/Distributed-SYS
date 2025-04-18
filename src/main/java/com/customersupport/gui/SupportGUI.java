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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SupportGUI extends JFrame {

    private JTextField userIdField, messageField, ticketIdField;
    private JTextArea consoleArea;
    private JButton sendButton, statusButton;

    private ChatbotServiceGrpc.ChatbotServiceBlockingStub chatbotStub;
    private TicketingServiceGrpc.TicketingServiceBlockingStub ticketingStub;
    private SentimentServiceGrpc.SentimentServiceStub sentimentStub;
    private StreamObserver<SentimentProto.SentimentRequest> sentimentStream;


    public SupportGUI() {
        // Ventana
        setTitle("Support GUI");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Channles gRPC
        ManagedChannel chatbotChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext().build();
        chatbotStub = ChatbotServiceGrpc.newBlockingStub(chatbotChannel);

        ManagedChannel ticketingChannel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext().build();
        ticketingStub = TicketingServiceGrpc.newBlockingStub(ticketingChannel);
        ManagedChannel sentimentChannel = ManagedChannelBuilder.forAddress("localhost", 50053)
        .usePlaintext().build();
        sentimentStub = SentimentServiceGrpc.newStub(sentimentChannel);
        startSentimentStream();

        // Panel superior
        JPanel topPanel = new JPanel(new GridLayout(3, 2));
        topPanel.add(new JLabel("User ID:"));
        userIdField = new JTextField();
        topPanel.add(userIdField);

        topPanel.add(new JLabel("Message:"));
        messageField = new JTextField();
        topPanel.add(messageField);

        topPanel.add(new JLabel("Ticket ID:"));
        ticketIdField = new JTextField();
        topPanel.add(ticketIdField);

        add(topPanel, BorderLayout.NORTH);

        // Console
        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        add(new JScrollPane(consoleArea), BorderLayout.CENTER);

        // Bottons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        sendButton = new JButton("Send to Chatbot");
        statusButton = new JButton("Check Ticket Status");

        buttonPanel.add(sendButton);
        buttonPanel.add(statusButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // actions
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessageToChatbot();
            }
        });

        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkTicketStatus();
            }
        });

        setVisible(true);
    }

    private void sendMessageToChatbot() {
        String userId = userIdField.getText().trim();
        String message = messageField.getText().trim();

        if (userId.isEmpty() || message.isEmpty()) {
            consoleArea.append("Please write your user ID and your message\n");
            return;
        }

        String token = JwtUtil.generateToken(userId);
        
        // Enviar al servicio de sentimiento
        SentimentProto.SentimentRequest sentimentRequest = SentimentProto.SentimentRequest.newBuilder()
                .setUserId(userId)
                .setPhrase(message)
                .setToken(token)
                .build();
        sentimentStream.onNext(sentimentRequest);


        ChatbotProto.ChatRequest request = ChatbotProto.ChatRequest.newBuilder()
                .setUserId(userId)
                .setMessage(message)
                .setToken(token)
                .build();

        try {
            ChatbotProto.ChatResponse response = chatbotStub.sendMessage(request);

            consoleArea.append("🗨️ Chatbot: " + response.getReply() + "\n");

            if (response.getEscalate()) {
                // Extraer ID del ticket del mensaje (si está incluido)
                String[] parts = response.getReply().split(": ");
                if (parts.length > 1) {
                    String ticketId = parts[1].trim();
                    ticketIdField.setText(ticketId);
                    consoleArea.append("🎟️ Ticket generated: " + ticketId + "\n");
                } else {
                    consoleArea.append("⚠️ Escalating requested but any ID ticket was generated.\n");
                }
            }

        } catch (Exception ex) {
            consoleArea.append("❌ Error to contact Chatbot: " + ex.getMessage() + "\n");
        }
    }

    private void checkTicketStatus() {
        String userId = userIdField.getText().trim();
        String ticketId = ticketIdField.getText().trim();

        if (userId.isEmpty() || ticketId.isEmpty()) {
            consoleArea.append("⚠️ Provide  User ID and Ticket ID\n");
            return;
        }

        String token = JwtUtil.generateToken(userId);

        TicketingProto.TicketStatusRequest request = TicketingProto.TicketStatusRequest.newBuilder()
                .setTicketId(ticketId)
                .setToken(token)
                .build();

        try {
            TicketingProto.TicketStatusResponse response = ticketingStub.getTicketStatus(request);
            consoleArea.append(" Ticket Status: " + response.getStatus() + "\n");

        } catch (Exception ex) {
            consoleArea.append("❌ Error : " + ex.getMessage() + "\n");
        }
    }
    private void startSentimentStream() {
    sentimentStream = sentimentStub.analyzeSentiments(new StreamObserver<SentimentProto.SentimentResponse>() {
        @Override
        public void onNext(SentimentProto.SentimentResponse response) {
            SwingUtilities.invokeLater(() -> {
                consoleArea.append("Feeling detected: " +
                        response.getSentiment() + " (" + response.getPhrase() + ")\n");
            });
        }

        @Override
        public void onError(Throwable t) {
            consoleArea.append("❌ Error in sentimental analysis: " + t.getMessage() + "\n");
        }

        @Override
        public void onCompleted() {
            consoleArea.append("✅ Sentimental analysis completed\n");
        }
    });
}

    public static void main(String[] args) {
        new SupportGUI();
    }
}
