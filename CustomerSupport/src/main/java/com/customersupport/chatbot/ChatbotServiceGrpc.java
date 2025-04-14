package com.customersupport.chatbot;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: chatbot.proto")
public final class ChatbotServiceGrpc {

  private ChatbotServiceGrpc() {}

  public static final String SERVICE_NAME = "ChatbotService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.customersupport.chatbot.ChatbotProto.ChatRequest,
      com.customersupport.chatbot.ChatbotProto.ChatResponse> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendMessage",
      requestType = com.customersupport.chatbot.ChatbotProto.ChatRequest.class,
      responseType = com.customersupport.chatbot.ChatbotProto.ChatResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.customersupport.chatbot.ChatbotProto.ChatRequest,
      com.customersupport.chatbot.ChatbotProto.ChatResponse> getSendMessageMethod() {
    io.grpc.MethodDescriptor<com.customersupport.chatbot.ChatbotProto.ChatRequest, com.customersupport.chatbot.ChatbotProto.ChatResponse> getSendMessageMethod;
    if ((getSendMessageMethod = ChatbotServiceGrpc.getSendMessageMethod) == null) {
      synchronized (ChatbotServiceGrpc.class) {
        if ((getSendMessageMethod = ChatbotServiceGrpc.getSendMessageMethod) == null) {
          ChatbotServiceGrpc.getSendMessageMethod = getSendMessageMethod = 
              io.grpc.MethodDescriptor.<com.customersupport.chatbot.ChatbotProto.ChatRequest, com.customersupport.chatbot.ChatbotProto.ChatResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ChatbotService", "SendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.chatbot.ChatbotProto.ChatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.chatbot.ChatbotProto.ChatResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatbotServiceMethodDescriptorSupplier("SendMessage"))
                  .build();
          }
        }
     }
     return getSendMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatbotServiceStub newStub(io.grpc.Channel channel) {
    return new ChatbotServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatbotServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatbotServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatbotServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatbotServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ChatbotServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendMessage(com.customersupport.chatbot.ChatbotProto.ChatRequest request,
        io.grpc.stub.StreamObserver<com.customersupport.chatbot.ChatbotProto.ChatResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.customersupport.chatbot.ChatbotProto.ChatRequest,
                com.customersupport.chatbot.ChatbotProto.ChatResponse>(
                  this, METHODID_SEND_MESSAGE)))
          .build();
    }
  }

  /**
   */
  public static final class ChatbotServiceStub extends io.grpc.stub.AbstractStub<ChatbotServiceStub> {
    private ChatbotServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatbotServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatbotServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatbotServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendMessage(com.customersupport.chatbot.ChatbotProto.ChatRequest request,
        io.grpc.stub.StreamObserver<com.customersupport.chatbot.ChatbotProto.ChatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ChatbotServiceBlockingStub extends io.grpc.stub.AbstractStub<ChatbotServiceBlockingStub> {
    private ChatbotServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatbotServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatbotServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatbotServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.customersupport.chatbot.ChatbotProto.ChatResponse sendMessage(com.customersupport.chatbot.ChatbotProto.ChatRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ChatbotServiceFutureStub extends io.grpc.stub.AbstractStub<ChatbotServiceFutureStub> {
    private ChatbotServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatbotServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatbotServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatbotServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.customersupport.chatbot.ChatbotProto.ChatResponse> sendMessage(
        com.customersupport.chatbot.ChatbotProto.ChatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_MESSAGE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChatbotServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChatbotServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((com.customersupport.chatbot.ChatbotProto.ChatRequest) request,
              (io.grpc.stub.StreamObserver<com.customersupport.chatbot.ChatbotProto.ChatResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ChatbotServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatbotServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.customersupport.chatbot.ChatbotProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChatbotService");
    }
  }

  private static final class ChatbotServiceFileDescriptorSupplier
      extends ChatbotServiceBaseDescriptorSupplier {
    ChatbotServiceFileDescriptorSupplier() {}
  }

  private static final class ChatbotServiceMethodDescriptorSupplier
      extends ChatbotServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ChatbotServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ChatbotServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatbotServiceFileDescriptorSupplier())
              .addMethod(getSendMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
