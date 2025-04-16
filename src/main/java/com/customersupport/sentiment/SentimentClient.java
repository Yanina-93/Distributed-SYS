/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customersupport.sentiment;

/**
 *
 * @author yani_
 */
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SentimentClient {
    public static void main (String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50053)
                .usePlaintext()
                .build();

        SentimentServiceGrpc.SentimentServiceStub asyncStub = SentimentServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);
        Scanner scanner = new Scanner(System.in);

        // Start bi directinal channel
        StreamObserver<SentimentProto.SentimentRequest> requestObserver =
                asyncStub.analyzeSentiments(new StreamObserver<SentimentProto.SentimentResponse>() {
                    @Override
                    public void onNext(SentimentProto.SentimentResponse response) {
                        System.out.println("Analized phrase: \"" + response.getPhrase() + "\"");
                        System.out.println("➡️  Sentiment detected: " + response.getSentiment());
                        System.out.println("-----------------------------");
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.err.println("❌ Error: " + t.getMessage());
                        latch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("✅ Analysis completed.");
                        latch.countDown();
                    }
                });

        System.out.println("🟢 Analysis initializing. Write some phrases.");
        System.out.println("Write down 'exit' to finish.\n");

        while (true) {
            System.out.print("Your phrase: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                requestObserver.onCompleted();
                break;
            }

            SentimentProto.SentimentRequest request = SentimentProto.SentimentRequest.newBuilder()
                    .setUserId("user1")
                    .setPhrase(input)
                    .build();

            requestObserver.onNext(request);
        }

        latch.await(5, TimeUnit.SECONDS);
        channel.shutdown();
    }
}

