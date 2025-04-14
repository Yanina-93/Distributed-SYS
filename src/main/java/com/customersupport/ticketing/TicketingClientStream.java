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
                .setIssueDescription("Problema al cargar la página")
                .build();

        System.out.println("🟢 Enviando solicitud de creación de tickets...");

        asyncStub.createTicketStream(request, new StreamObserver<TicketingProto.TicketResponse>() {
            @Override
            public void onNext(TicketingProto.TicketResponse response) {
                System.out.println("📩 Ticket generado:");
                System.out.println(" - ID: " + response.getTicketId());
                System.out.println(" - Estado: " + response.getStatus());
                System.out.println("-----------------------------");
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("❌ Error durante el streaming: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("✅ Todos los tickets generados.");
                channel.shutdown();
            }
        });

        // Esperar para que se reciba todo el streaming antes de que el programa termine
        try {
            Thread.sleep(5000); // Ajustar si se generan más tickets
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
