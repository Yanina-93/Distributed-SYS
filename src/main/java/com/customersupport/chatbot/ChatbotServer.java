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
import java.io.FileWriter;
import java.util.List;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;



//Connecting to ticketing service
import com.customersupport.ticketing.TicketingProto;
import com.customersupport.ticketing.TicketingServiceGrpc;

//Connecting to sentiment service
import com.customersupport.sentiment.SentimentProto;
import com.customersupport.sentiment.SentimentServiceGrpc;

//Connecting JWT
import com.customersupport.auth.JwtUtil;


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

    static class ChatbotServiceImpl extends ChatbotServiceGrpc.ChatbotServiceImplBase {
        
        private final TicketingServiceGrpc.TicketingServiceBlockingStub ticketingStub;
        private final SentimentServiceGrpc.SentimentServiceStub sentimentStub;

        public ChatbotServiceImpl() {
            
            //Ticketing
            ManagedChannel ticketchannel = ManagedChannelBuilder
                    .forAddress("localhost", 50052) // same port of TicketingServer
                    .usePlaintext()
                    .build();
            ticketingStub = TicketingServiceGrpc.newBlockingStub(ticketchannel);
            
            //Sentiment
            ManagedChannel sentimentChannel = ManagedChannelBuilder
                    .forAddress("localhost", 50053) // same port of SentimentServer
                    .usePlaintext()
                    .build();
            sentimentStub = SentimentServiceGrpc.newStub(sentimentChannel);
        }

        public void analyzeEmotionStream(List<String> phrases, String userId, String token){
            CountDownLatch latch = new CountDownLatch(1);
            
            StreamObserver<SentimentProto.SentimentRequest> requestObserver = sentimentStub.analyzeSentiments(new StreamObserver<SentimentProto.SentimentResponse>(){
                @Override
                public void onNext(SentimentProto.SentimentResponse response){
                    System.out.println("[Emotion] \"" + response.getPhrase() + "\"->" + response.getSentiment().toUpperCase());
                }
                
                @Override
                public void onError(Throwable t){
                    System.err.println("X Error in analysis: " + t.getMessage());
                    latch.countDown();
                }
                
                @Override
                public void onCompleted(){
                    System.out.println(" Analysis completed.");
                    latch.countDown();
                }
            });
            
            for (String phrase : phrases){
                SentimentProto.SentimentRequest request = SentimentProto.SentimentRequest.newBuilder()
                        .setUserId(userId)
                        .setPhrase(phrase)
                        .setToken(token)
                        .build();
                requestObserver.onNext(request);
            }
            
            requestObserver.onCompleted();
            try{
                latch.await(3, TimeUnit.SECONDS);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        
        @Override
        public void sendMessage(ChatbotProto.ChatRequest request,
                                StreamObserver<ChatbotProto.ChatResponse> responseObserver) {
            
            try{
                 //JWT validation
                if (!JwtUtil.validateToken(request.getToken())) {
                    responseObserver.onError(Status.UNAUTHENTICATED
                            .withDescription("Invalid Token").asRuntimeException());
                return;}
                System.out.println("📡 TOKEN : " + request.getToken());
                System.out.println("💡 ticketingStub es null? " + (ticketingStub == null));
                System.out.println("💡 sentimentStub es null? " + (sentimentStub == null));
            }catch (Exception e) {
                   e.printStackTrace();
                   responseObserver.onError(io.grpc.Status.INTERNAL
                        .withDescription("Error")
                        .augmentDescription(e.getMessage())
                        .asRuntimeException());
            }
            
            
            String userMessage = request.getMessage().toLowerCase();
            String reply;
            boolean escalate = false;
            
            //Get sentiment
            analyzeEmotionStream(List.of(userMessage), request.getUserId(),request.getToken());         
            
           
            if (userMessage.contains("hello") || userMessage.contains("hi")) {
                reply = "Hello! How can I help you?";
                
            } else if (userMessage.contains("problem") || userMessage.contains("error")|| userMessage.contains("mistake")|| userMessage.contains("missunderstanding")) {
                escalate = true;
                
                //Now we create the ticket for the request
                TicketingProto.TicketRequest ticketRequest = TicketingProto.TicketRequest.newBuilder()
                        .setUserId(request.getUserId())
                        .setIssueDescription(request.getMessage())
                        .setToken(request.getToken())
                        .build();
                
                TicketingProto.TicketResponse ticketResponse = ticketingStub.createTicket(ticketRequest);
                String ticketId = ticketResponse.getTicketId();
                
                
                reply = "So sorry about that. I created a ticket for you. Ticket ID is: " + ticketResponse.getTicketId();
                
                
            } else if(userMessage.contains("Thanks") || userMessage.contains("thank you")){
                reply = "No worries. I'm here to help you";
            } else {
                reply = "Sorry, I couldn't understand your message, Can you write it again?";
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

