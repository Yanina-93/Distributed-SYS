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
import io.grpc.Status;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Connecting JWT
import com.customersupport.auth.JwtUtil;

//SQL



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
            
            //JWT Validation 
            if (!JwtUtil.validateToken(request.getToken())) {
                responseObserver.onError(Status.UNAUTHENTICATED
                    .withDescription("Invalid Token").asRuntimeException());
                return;
            }
            String ticketId = UUID.randomUUID().toString();
            ///SQLite
            
            try (Connection conn = SQLiteUtil.getConnection()) {
                 String sql = "CREATE TABLE IF NOT EXISTS tickets (" +
                                "id TEXT PRIMARY KEY, " +
                                "user_id TEXT NOT NULL, " +
                                "description TEXT NOT NULL, " +
                                "status TEXT NOT NULL" +
                                ")";
                conn.createStatement().execute(sql);
                System.out.println("✅ table for tickets ready");
            } catch (SQLException e) {
                System.err.println("❌ Error " + e.getMessage());
            }

            
            
            ///String ticketId = UUID.randomUUID().toString();
            
            ///Ticket ticket = new Ticket(ticketId, request.getUserId(), request.getIssueDescription());
            ///ticketList.add(ticket);

            ///System.out.println("New ticket ID: "+ ticketId + " |User: " + request.getUserId());

            ///TicketingProto.TicketResponse response = TicketingProto.TicketResponse.newBuilder()
            ///        .setTicketId(ticketId)
            ///        .setStatus(ticket.status)
            ///        .build();
            ///responseObserver.onNext(response);
            ///responseObserver.onCompleted();
                        
            //WE RE WRITE THE METHOD FOR THE SQL DATA

            try (Connection conn = SQLiteUtil.getConnection()) {
                // create table if it doesnt exists
                String createTableSql = "CREATE TABLE IF NOT EXISTS tickets (" +
                        "id TEXT PRIMARY KEY, " +
                        "user_id TEXT NOT NULL, " +
                        "description TEXT NOT NULL, " +
                        "status TEXT NOT NULL)";
                conn.createStatement().execute(createTableSql);

                // new ticket
                String insertSql = "INSERT INTO tickets (id, user_id, description, status) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(insertSql);
                ps.setString(1, ticketId);
                ps.setString(2, request.getUserId());
                ps.setString(3, request.getIssueDescription());
                ps.setString(4, "Open");
                ps.executeUpdate();

                // send response
                TicketingProto.TicketResponse response = TicketingProto.TicketResponse.newBuilder()
                        .setTicketId(ticketId)
                        .setStatus("Open")
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();

            } catch (SQLException e) {
                e.printStackTrace();
                responseObserver.onError(io.grpc.Status.INTERNAL
                        .withDescription("Error in saving data")
                        .augmentDescription(e.getMessage())
                        .asRuntimeException());
            }
        }
    

        //RPC Unary -- ticket's status
        @Override
        public void getTicketStatus(TicketingProto.TicketStatusRequest request, StreamObserver<TicketingProto.TicketStatusResponse> responseObserver){
            //JWT Validation 
            if (!JwtUtil.validateToken(request.getToken())) {
                responseObserver.onError(Status.UNAUTHENTICATED
                    .withDescription("Invalid Token").asRuntimeException());
                return;
            }
            
            ///String status =  "Not Found";
            ///for(Ticket t : ticketList){
            ///    if(t.id.equals(request.getTicketId())){
            ///        status = t.status;
            ///        break;
            ///    }
            ///}
            
            ///TicketingProto.TicketStatusResponse response = TicketingProto.TicketStatusResponse.newBuilder()
            ///        .setStatus(status)
            ///        .build();
            ///responseObserver.onNext(response);
            ///responseObserver.onCompleted();
            
            ///WE REWRITE THE METHOD FOR THE DATABASE
            
           try (Connection conn = SQLiteUtil.getConnection()) {
            
            String createTableSql = "CREATE TABLE IF NOT EXISTS tickets (" +
                    "id TEXT PRIMARY KEY, " +
                    "user_id TEXT NOT NULL, " +
                    "description TEXT NOT NULL, " +
                    "status TEXT NOT NULL)";
            conn.createStatement().execute(createTableSql);

            
            String selectSql = "SELECT status FROM tickets WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(selectSql);
            ps.setString(1, request.getTicketId());

            ResultSet rs = ps.executeQuery();

            String status;
            if (rs.next()) {
                status = rs.getString("status");
            } else {
                status = "No encontrado";
            }

            //send response
            TicketingProto.TicketStatusResponse response = TicketingProto.TicketStatusResponse.newBuilder()
                    .setStatus(status)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            } catch (SQLException e) {
                e.printStackTrace();
                responseObserver.onError(io.grpc.Status.INTERNAL
                        .withDescription("Error consulting data base")
                        .augmentDescription(e.getMessage())
                        .asRuntimeException());
            }
        }
        
        //RPC Server Streaming --Multiple tickets
        
        public StreamObserver<TicketingProto.TicketRequest> createTicketStream(final StreamObserver<TicketingProto.TicketResponse> responseObserver){
            
            return new StreamObserver<TicketingProto.TicketRequest>(){
                StringBuilder summary = new StringBuilder();
                String token = null;

                @Override
                public void onNext(TicketingProto.TicketRequest request) {
                    if (token == null) token = request.getToken();

                    if (!JwtUtil.validateToken(request.getToken())) {
                        responseObserver.onError(io.grpc.Status.UNAUTHENTICATED
                                .withDescription("Invalid Token")
                                .asRuntimeException());
                        return;
                    }

                    String ticketId = UUID.randomUUID().toString();

                    try (Connection conn = SQLiteUtil.getConnection()) {
                        String insertSql = "INSERT INTO tickets (id, user_id, description, status) VALUES (?, ?, ?, ?)";
                        PreparedStatement ps = conn.prepareStatement(insertSql);
                        ps.setString(1, ticketId);
                        ps.setString(2, request.getUserId());
                        ps.setString(3, request.getIssueDescription());
                        ps.setString(4, "Open");
                        ps.executeUpdate();

                        summary.append("Created: ").append(ticketId).append("\n");

                    } catch (SQLException e) {
                        summary.append("❌ Error: ").append(e.getMessage()).append("\n");
                    }
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("X Error : " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    TicketingProto.TicketResponse response = TicketingProto.TicketResponse.newBuilder()
                            .setTicketId("Multiple")
                            .setStatus(summary.toString())
                            .build();

                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            };

        }
        
        //RPC Client Streaming -- Multiple checking status       
        @Override
        public StreamObserver<TicketingProto.TicketStatusRequest> checkMultipleStatuses ( final StreamObserver<TicketingProto.TicketStatusBatchResponse> responseObserver){
                       
            return new StreamObserver<TicketingProto.TicketStatusRequest>(){
                String token = null;
                List<String> statuses = new ArrayList<>();
                
                @Override
                public void onNext(TicketingProto.TicketStatusRequest request) {
                    if (token == null){ token = request.getToken();}

                    if (!JwtUtil.validateToken(request.getToken())) {
                        responseObserver.onError(io.grpc.Status.UNAUTHENTICATED
                                .withDescription("Invalid token")
                                .asRuntimeException());
                        return;
                    }

                    try (Connection conn = SQLiteUtil.getConnection()) {
                        String selectSql = "SELECT status FROM tickets WHERE id = ?";
                        PreparedStatement ps = conn.prepareStatement(selectSql);
                        ps.setString(1, request.getTicketId());
                        ResultSet rs = ps.executeQuery();

                        String status = rs.next()
                                ? "Ticket " + request.getTicketId() + ": " + rs.getString("status")
                                : "Ticket " + request.getTicketId() + ": Not found";
                        
                        statuses.add(status);
                    } catch(SQLException e){
                        statuses.add("Error consulting ticket " + request.getTicketId() + ": " + e.getMessage());
                    }
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("X Error in multiple checking: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    TicketingProto.TicketStatusBatchResponse response =
                    TicketingProto.TicketStatusBatchResponse.newBuilder()
                            .addAllStatuses(statuses)
                            .build();

                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            };
        
        }
        @Override
        public void closeTicket(TicketingProto.TicketCloseRequest request,
                                StreamObserver<TicketingProto.TicketCloseResponse> responseObserver) {

            if (!JwtUtil.validateToken(request.getToken())) {
                responseObserver.onError(io.grpc.Status.UNAUTHENTICATED
                        .withDescription("Invalid token")
                        .asRuntimeException());
                return;
            }

            try (Connection conn = SQLiteUtil.getConnection()) {
                String sql = "UPDATE tickets SET status = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, "Closed");
                ps.setString(2, request.getTicketId());

                int updated = ps.executeUpdate();

                String message = (updated > 0) ? "✅ Ticket closed succesfully"
                                               : "❌ Ticket not found";

                TicketingProto.TicketCloseResponse response = TicketingProto.TicketCloseResponse.newBuilder()
                        .setMessage(message)
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();

            } catch (SQLException e) {
                responseObserver.onError(io.grpc.Status.INTERNAL
                        .withDescription("Error in database")
                        .augmentDescription(e.getMessage())
                        .asRuntimeException());
            }
        }

    }
}