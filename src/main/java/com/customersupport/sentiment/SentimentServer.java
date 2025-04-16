/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customersupport.sentiment;

/**
 *
 * @author yani_
 */

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import io.grpc.Status;

//Connecting JWT
import com.customersupport.auth.JwtUtil;

public class SentimentServer {
   public static void main(String[] args) throws Exception{
       Server server = ServerBuilder.forPort(50053)
               .addService(new SentimentServiceImpl())
               .build();
       System.out.println("SentimentAnalysis server initializing in port 50053");
       server.start();
       server.awaitTermination();
       
   } 
   
   static class SentimentServiceImpl extends SentimentServiceGrpc.SentimentServiceImplBase{
       @Override
       public StreamObserver<SentimentProto.SentimentRequest> analyzeSentiments(StreamObserver<SentimentProto.SentimentResponse> responseObserver){
           return new StreamObserver<SentimentProto.SentimentRequest>(){
                @Override
                public void onNext(SentimentProto.SentimentRequest request){
                                //JWT Validation 
                    if (!JwtUtil.validateToken(request.getToken())) {
                        responseObserver.onError(Status.UNAUTHENTICATED
                            .withDescription("Invalid Token").asRuntimeException());
                         return;
                    }
                    String phrase = request.getPhrase().toLowerCase(Locale.ROOT);
                    String result;
                    
                    if(phrase.contains("good") || phrase.contains("excelent") || phrase.contains("happy")){
                        result = "positive";                        
                    } else if(phrase.contains("bad") || phrase.contains("terrible") || phrase.contains("sad")){
                        result = "negative";
                    } else{
                        result = "neutral";
                    }
                    logToFile(request.getUserId(), phrase, result);
                    
                    SentimentProto.SentimentResponse response = SentimentProto.SentimentResponse.newBuilder()
                            .setPhrase(phrase)
                            .setSentiment(result)
                            .build();
                    responseObserver.onNext(response);
                    
                }
                
                @Override
                public void onError(Throwable t){
                    System.err.println("X Error in streaming analysis: "+ t.getMessage());
                }
                
                @Override
                public void onCompleted(){
                    responseObserver.onCompleted();
                }
                
                private void logToFile(String userId, String phrase, String result){
                    try(FileWriter writer = new FileWriter("sentiment_logs.txt", true)){
                        writer.write("User: " + userId +"\n");
                        writer.write("Phrase: " + phrase +"\n");
                        writer.write("Sentiment: " + result +"\n");
                        writer.write("---------------------\n");
                    } catch (IOException e){
                        System.err.println("X Transcription couldn't be make it: " + e.getMessage());
                    }
                }
           };
        

       }
   
   }
}
