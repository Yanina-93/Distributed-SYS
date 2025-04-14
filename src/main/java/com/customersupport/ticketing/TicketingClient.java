package com.customersupport.ticketing;
import com.customersupport.ticketing.TicketingServiceGrpc;


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
        CountDownLatch latch = new CountDownLatch(1);
        
        System.out.println("Sending ticket IDs check status.");
        System.out.println("Write down the ticket's ID one by one. \n"+ "Please write 'done' to finish and get the results. \n");
        
        StreamObserver<TicketingProto.TicketStatusRequest> requestObserver = asyncStub.checkMultipleStatuses(new StreamObserver<TicketingProto.TicketStatusBatchResponse>(){
            @Override
            public void onNext(TicketingProto.TicketStatusBatchResponse response){
                System.out.println("STatuses gotten: ");
                for (String s : response.getStatusesList()){
                    System.out.println("- " + s);
                }
            }
            
            @Override
            public void onError(Throwable t){
                System.err.println("X Error consulting statuses: " + t.getMessage());
                latch.countDown();
            }
            
            @Override
            public void onCompleted(){
                System.out.println("Checking finished.");
                latch.countDown();
            }
        });
    
        while(true){
            System.out.println("TicketID: ");
            String input = sc.nextLine().trim();
            
            if(input.equalsIgnoreCase("donde"))
                break;
            if(!input.isEmpty()){
                TicketingProto.TicketStatusRequest request = TicketingProto.TicketStatusRequest.newBuilder()
                        .setTicketId(input)
                        .build();
                requestObserver.onNext(request);
            }                  
                  
        }
        
        requestObserver.onCompleted();
        latch.await(5, TimeUnit.SECONDS);
        channel.shutdown();
                
    }
             
    
}
