/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customersupport.ticketing;

/**
 *
 * @author yani_
 */
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class TicketingServer{
    public static void main (String[]args) throws IOException, InterruptedException{
        Server server = ServerBuilder
                .forPort(50052)
                .addService(new TicketingServiceImpl())
                .build();
        System.out.println("Ticketing Server initializing on port 50052");
        server.start();
        server.awaitTermination();    
    }
   
    
    //SERVICE
    static class TicketingServiceImpl extends TicketingServiceGrpc.TicketingServiceImplBase{
        private final List<Ticket> ticketList = new ArrayList<>();

        //first we define the ticket object
        class Ticket{
            String id;
            String userId;
            String description;
            String status;
            public Ticket(String id, String userId, String description){
                this.id = id;
                this.userId = userId;
                this.description = description;
                this.status = "Open";        
            }
        }                
        //RPC Unary -- one ticket
        
        @Override
        public void createTicket(TicketingProto.TicketRequest request, StreamObserver<TicketingProto.TicketResponse> responseObserver){
            String ticketId = UUID.randomUUID().toString();
            Ticket ticket = new Ticket(ticketId, request.getUserId(), request.getIssueDescription());
            ticketList.add(ticket);

            System.out.println("New ticket ID: "+ ticketId + " |User: " + request.getUserId());

            TicketingProto.TicketResponse response = TicketingProto.TicketResponse.newBuilder()
                    .setTicketId(ticketId)
                    .setStatus(ticket.status)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        //RPC Unary -- ticket's status
        @Override
        public void getTicketStatus(TicketingProto.TicketStatusRequest request, StreamObserver<TicketingProto.TicketStatusResponse> responseObserver){
            String status =  "Not Found";
            for(Ticket t : ticketList){
                if(t.id.equals(request.getTicketId())){
                    status = t.status;
                    break;
                }
            }
            
            TicketingProto.TicketStatusResponse response = TicketingProto.TicketStatusResponse.newBuilder()
                    .setStatus(status)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        
        //RPC Server Streaming --Multiple tickets
        @Override
        public void createTicketStream(TicketingProto.TicketRequest request, StreamObserver<TicketingProto.TicketResponse> responseObserver){
            for(int i = 0; i < 3; i ++){ //simulation mulple tickets created
                String ticketId = UUID.randomUUID().toString();
                Ticket ticket = new Ticket(ticketId, request.getUserId(), request.getIssueDescription() + " #" + (i+1));
                ticketList.add(ticket);

                System.out.println("New ticket ID: "+ ticketId + " |User: " + request.getUserId());

                TicketingProto.TicketResponse response = TicketingProto.TicketResponse.newBuilder()
                        .setTicketId(ticketId)
                        .setStatus(ticket.status)
                        .build();
                responseObserver.onNext(response);
                
                try{
                    Thread.sleep(1000); // one sec between tickets
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
       
            }
            responseObserver.onCompleted();
        }
        
        //RPC Client Streaming -- Multiple checking status       
        @Override
        public StreamObserver<TicketingProto.TicketStatusRequest> checkMultipleStatuses ( final StreamObserver<TicketingProto.TicketStatusBatchResponse> responseObserver){
            
            List<String> statuses = new ArrayList<>();
            
            return new StreamObserver<TicketingProto.TicketStatusRequest>(){
                @Override
                public void onNext(TicketingProto.TicketStatusRequest request){
                    String status = "Not found.";
                    
                    for(Ticket t : ticketList) {
                        if(t.id.equals(request.getTicketId())){
                            status = t.status;
                            break;
                        }
                    }
                    
                    statuses.add("ID: " + request.getTicketId() + "| Status: " + status);
                }
                
                @Override
                public void onError(Throwable t){
                    System.err.println("Error in CLient Streaming: "+ t.getMessage());
                }
                
                
                @Override
                public void onCompleted(){
                    TicketingProto.TicketStatusBatchResponse response = TicketingProto.TicketStatusBatchResponse.newBuilder()
                            .addAllStatuses(statuses)
                            .build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();                    
                }           
            
            };
        
        }
    }
}