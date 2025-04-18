package com.customersupport.ticketing;
import com.customersupport.ticketing.TicketingServiceGrpc;
import com.customersupport.auth.JwtUtil;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yani_
 * 
 * TESTING CLIENT CLASS
 */

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TicketingClient {
    
    public static void main(String[] args) throws InterruptedException{
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50052)
                .usePlaintext()
                .build();
        TicketingServiceGrpc.TicketingServiceStub asyncStub = TicketingServiceGrpc.newStub(channel);
        
        Scanner sc = new Scanner(System.in);
        TicketingServiceGrpc.TicketingServiceBlockingStub blockingStub = TicketingServiceGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        String userId = "user123";
        String token = JwtUtil.generateToken(userId); // token

        System.out.println("Welcome to our  ticket's system.");
        System.out.println("1. Create a ticket");
        System.out.println("2. Checj tiket's status");
        System.out.print("Select an option: ");
        String opcion = scanner.nextLine();

        if (opcion.equals("1")) {
            System.out.print("Describe the problem: ");
            String issue = scanner.nextLine();

            TicketingProto.TicketRequest request = TicketingProto.TicketRequest.newBuilder()
                    .setUserId(userId)
                    .setIssueDescription(issue)
                    .setToken(token) // auto token
                    .build();

            TicketingProto.TicketResponse response = blockingStub.createTicket(request);
            System.out.println("✅ Ticket created. ID: " + response.getTicketId());
            System.out.println("📌 Initail Status: " + response.getStatus());

        } else if (opcion.equals("2")) {
            System.out.print("Input ID ticket: ");
            String ticketId = scanner.nextLine();

            TicketingProto.TicketStatusRequest request = TicketingProto.TicketStatusRequest.newBuilder()
                    .setTicketId(ticketId)
                    .setToken(token) // auto token
                    .build();

            TicketingProto.TicketStatusResponse response = blockingStub.getTicketStatus(request);
            System.out.println("📌 Ticket status: " + response.getStatus());
        } else {
            System.out.println("❌ Invalid option");
        }

        channel.shutdown();
        scanner.close();
    }
}
    //    CountDownLatch latch = new CountDownLatch(1);
        
//        System.out.println("Sending ticket IDs check status.");
//        System.out.println("Write down the ticket's ID one by one. \n"+ "Please write 'done' to finish and get the results. \n");
//        
//        StreamObserver<TicketingProto.TicketStatusRequest> requestObserver = asyncStub.checkMultipleStatuses(new StreamObserver<TicketingProto.TicketStatusBatchResponse>(){
//            @Override
//            public void onNext(TicketingProto.TicketStatusBatchResponse response){
//                System.out.println("STatuses gotten: ");
//                for (String s : response.getStatusesList()){
//                    System.out.println("- " + s);
//                }
//            }
//            
//            @Override
//            public void onError(Throwable t){
//                System.err.println("X Error consulting statuses: " + t.getMessage());
//                latch.countDown();
//            }
//            
//            @Override
//            public void onCompleted(){
//                System.out.println("Checking finished.");
//                latch.countDown();
//            }
//        });
//    
//        while(true){
//            System.out.println("TicketID: ");
//            String input = sc.nextLine().trim();
//            
//            if(input.equalsIgnoreCase("done"))
//                break;
//            if(!input.isEmpty()){
//                TicketingProto.TicketStatusRequest request = TicketingProto.TicketStatusRequest.newBuilder()
//                        .setTicketId(input)
//                        .build();
//                requestObserver.onNext(request);
//            }                  
//                  
//        }
//        
//        requestObserver.onCompleted();
//        latch.await(5, TimeUnit.SECONDS);
//        channel.shutdown();
//                
    
             
    

