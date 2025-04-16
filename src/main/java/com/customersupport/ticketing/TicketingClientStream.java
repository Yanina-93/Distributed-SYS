/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customersupport.ticketing;
import com.customersupport.ticketing.TicketingServiceGrpc;

/**
 *
 * @author yani_
 */

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class TicketingClientStream {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        TicketingServiceGrpc.TicketingServiceStub asyncStub = TicketingServiceGrpc.newStub(channel);

        TicketingProto.TicketRequest request = TicketingProto.TicketRequest.newBuilder()
                .setUserId("user42")
                .setIssueDescription("Problem loading the page")
                .build();

        System.out.println("🟢 Sending request of creating tickets...");

        asyncStub.createTicketStream(request, new StreamObserver<TicketingProto.TicketResponse>() {
            @Override
            public void onNext(TicketingProto.TicketResponse response) {
                System.out.println("📩 Ticket generated:");
                System.out.println(" - ID: " + response.getTicketId());
                System.out.println(" - Status: " + response.getStatus());
                System.out.println("-----------------------------");
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("❌ Error in streaming: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("✅ All the tickets generated.");
                channel.shutdown();
            }
        });

        // Wait for the streaming
        try {
            Thread.sleep(5000); // change if the numbers of tickets got bigger
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
