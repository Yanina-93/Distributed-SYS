/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customersupport.test;

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

public class FullSystemTest {

    public static void main(String[] args) throws InterruptedException {
        String userId = "user_test";
        String token = JwtUtil.generateToken(userId);
        String testMessage = "I have a terrible problem";

        // 1. cnnecting to chatbot
        ManagedChannel chatbotChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext().build();
        ChatbotServiceGrpc.ChatbotServiceBlockingStub chatbotStub = ChatbotServiceGrpc.newBlockingStub(chatbotChannel);

        ChatbotProto.ChatRequest request = ChatbotProto.ChatRequest.newBuilder()
                .setUserId(userId)
                .setMessage(testMessage)
                .setToken(token)
                .build();

        ChatbotProto.ChatResponse response = chatbotStub.sendMessage(request);
        System.out.println("🤖 Chatbot: " + response.getReply());
        System.out.println("📢 Escalate?: " + response.getEscalate());

        // 2. ticket generated?; check validation
        if (response.getEscalate()) {
            ManagedChannel ticketChannel = ManagedChannelBuilder.forAddress("localhost", 50052)
                    .usePlaintext().build();
            TicketingServiceGrpc.TicketingServiceBlockingStub ticketStub = TicketingServiceGrpc.newBlockingStub(ticketChannel);

            // ID simulated
            String[] parts = response.getReply().split(": ");
            String ticketId = parts.length > 1 ? parts[1].trim() : null;

            if (ticketId != null) {
                TicketingProto.TicketStatusRequest statusRequest = TicketingProto.TicketStatusRequest.newBuilder()
                        .setTicketId(ticketId)
                        .setToken(token)
                        .build();

                TicketingProto.TicketStatusResponse statusResponse = ticketStub.getTicketStatus(statusRequest);
                System.out.println("Ticket status: " + statusResponse.getStatus());
            }

            ticketChannel.shutdown();
        }

        chatbotChannel.shutdown();
    }
}

