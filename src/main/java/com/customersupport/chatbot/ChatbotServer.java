/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customersupport.chatbot;

/**
 *
 * @author yani_
 */

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.FileWriter;
import java.io.IOException;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;


//Conecting to ticketing service
import com.customersupport.ticketing.TicketingProto;
import com.customersupport.ticketing.TicketingServiceGrpc;



public class ChatbotServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(50051)
                .addService(new ChatbotServiceImpl())
                .build();

        System.out.println("🟢 Chatbot Server initializing on port 50051");
        server.start();
        server.awaitTermination();
    }

    static class ChatbotServiceImpl extends ChatbotServiceGrpc.ChatbotServiceImplBase {/////Change here
        
        private final TicketingServiceGrpc.TicketingServiceBlockingStub ticketingStub;

        public ChatbotServiceImpl() {
            ManagedChannel channel = ManagedChannelBuilder
                    .forAddress("localhost", 50052) // mismo puerto que TicketingServer
                    .usePlaintext()
                    .build();
            ticketingStub = TicketingServiceGrpc.newBlockingStub(channel);
        }

        
        
        @Override
        public void sendMessage(ChatbotProto.ChatRequest request,
                                StreamObserver<ChatbotProto.ChatResponse> responseObserver) {

            String userMessage = request.getMessage().toLowerCase();
            String reply;
            boolean escalate = false;

            if (userMessage.contains("hello") || userMessage.contains("hi")) {
                reply = "Hello! How can I help you?";
                
            } else if (userMessage.contains("problem") || userMessage.contains("error")) {
                escalate = true;
                
                //Now we create the ticket for the request
                TicketingProto.TicketRequest ticketRequest = TicketingProto.TicketRequest.newBuilder()
                        .setUserId(request.getUserId())
                        .setIssueDescription(request.getMessage())
                        .build();
                
                TicketingProto.TicketResponse ticketResponse = ticketingStub.createTicket(ticketRequest);
                String ticketId = ticketResponse.getTicketId();
                
                
                reply = "So sorry about that. I created a ticket for you. Ticket ID is: " + ticketId;
                
                
            } else if(userMessage.contains("Thanks") || userMessage.contains("thank you")){
                reply = "No worries. I'm here to help you";
            } else {
                reply = "Sorry, I could'nt understand your message, Can you write it again?";
            }

            ChatbotProto.ChatResponse response = ChatbotProto.ChatResponse.newBuilder()
                    .setReply(reply)
                    .setEscalate(escalate)
                    .build();
            
            System.out.println("LOG" + request.getUserId() + ": "+ request.getMessage());
            System.out.println("BOT: "+ reply + "| Escalate: " + escalate);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            logToFile(request.getUserId(), request.getMessage(), reply);
        }
        
        public void logToFile(String userId, String message, String reply){
            try(FileWriter writer  = new FileWriter("chatbot_logs.txt", true)){
                writer.write("User: " + userId + "\n");
                writer.write("Message: " + message + "\n");
                writer.write("Response: "+ reply + "\n");
                writer.write("--------------------- \n");
            }catch(IOException e){
                System.err.println("Transcription error: "+ e.getMessage());
            }
        }
        
        
    }
}

