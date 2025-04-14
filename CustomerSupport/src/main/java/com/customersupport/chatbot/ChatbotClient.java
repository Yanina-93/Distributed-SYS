/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customersupport.chatbot;

/**
 *
 * @author yani_
 */

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.customersupport.chatbot.ChatbotProto;
import com.customersupport.chatbot.ChatbotServiceGrpc;

import java.util.Scanner;

public class ChatbotClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ChatbotServiceGrpc.ChatbotServiceBlockingStub stub =
                ChatbotServiceGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        System.out.println("🗨️ You are connected. Please write your message:");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine();
            
            if(input.isEmpty()){
                System.out.println("Please write down your request.");
                continue;
            }else if (input.equalsIgnoreCase("Exit")) break;

            ChatbotProto.ChatRequest request = ChatbotProto.ChatRequest.newBuilder()
                    .setUserId("user1")
                    .setMessage(input)
                    .build();

            ChatbotProto.ChatResponse response = stub.sendMessage(request);
            System.out.println("🤖 Chatbot: " + response.getReply());

            if (response.getEscalate()) {
                System.out.println("⚠️ Message needs to scaling to agent support.");
            }
        }

        channel.shutdown();
        System.out.println("🔴 Client closed.");
    }
}

