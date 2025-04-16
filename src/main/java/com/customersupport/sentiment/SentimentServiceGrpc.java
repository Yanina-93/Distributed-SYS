package com.customersupport.sentiment;

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
    comments = "Source: Sentiment.proto")
public final class SentimentServiceGrpc {

  private SentimentServiceGrpc() {}

  public static final String SERVICE_NAME = "SentimentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.customersupport.sentiment.SentimentProto.SentimentRequest,
      com.customersupport.sentiment.SentimentProto.SentimentResponse> getAnalyzeSentimentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AnalyzeSentiments",
      requestType = com.customersupport.sentiment.SentimentProto.SentimentRequest.class,
      responseType = com.customersupport.sentiment.SentimentProto.SentimentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.customersupport.sentiment.SentimentProto.SentimentRequest,
      com.customersupport.sentiment.SentimentProto.SentimentResponse> getAnalyzeSentimentsMethod() {
    io.grpc.MethodDescriptor<com.customersupport.sentiment.SentimentProto.SentimentRequest, com.customersupport.sentiment.SentimentProto.SentimentResponse> getAnalyzeSentimentsMethod;
    if ((getAnalyzeSentimentsMethod = SentimentServiceGrpc.getAnalyzeSentimentsMethod) == null) {
      synchronized (SentimentServiceGrpc.class) {
        if ((getAnalyzeSentimentsMethod = SentimentServiceGrpc.getAnalyzeSentimentsMethod) == null) {
          SentimentServiceGrpc.getAnalyzeSentimentsMethod = getAnalyzeSentimentsMethod = 
              io.grpc.MethodDescriptor.<com.customersupport.sentiment.SentimentProto.SentimentRequest, com.customersupport.sentiment.SentimentProto.SentimentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "SentimentService", "AnalyzeSentiments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.sentiment.SentimentProto.SentimentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.sentiment.SentimentProto.SentimentResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SentimentServiceMethodDescriptorSupplier("AnalyzeSentiments"))
                  .build();
          }
        }
     }
     return getAnalyzeSentimentsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SentimentServiceStub newStub(io.grpc.Channel channel) {
    return new SentimentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SentimentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SentimentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SentimentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SentimentServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SentimentServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Bi-directional streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.customersupport.sentiment.SentimentProto.SentimentRequest> analyzeSentiments(
        io.grpc.stub.StreamObserver<com.customersupport.sentiment.SentimentProto.SentimentResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getAnalyzeSentimentsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAnalyzeSentimentsMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.customersupport.sentiment.SentimentProto.SentimentRequest,
                com.customersupport.sentiment.SentimentProto.SentimentResponse>(
                  this, METHODID_ANALYZE_SENTIMENTS)))
          .build();
    }
  }

  /**
   */
  public static final class SentimentServiceStub extends io.grpc.stub.AbstractStub<SentimentServiceStub> {
    private SentimentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SentimentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SentimentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SentimentServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Bi-directional streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.customersupport.sentiment.SentimentProto.SentimentRequest> analyzeSentiments(
        io.grpc.stub.StreamObserver<com.customersupport.sentiment.SentimentProto.SentimentResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getAnalyzeSentimentsMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class SentimentServiceBlockingStub extends io.grpc.stub.AbstractStub<SentimentServiceBlockingStub> {
    private SentimentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SentimentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SentimentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SentimentServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class SentimentServiceFutureStub extends io.grpc.stub.AbstractStub<SentimentServiceFutureStub> {
    private SentimentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SentimentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SentimentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SentimentServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_ANALYZE_SENTIMENTS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SentimentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SentimentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ANALYZE_SENTIMENTS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.analyzeSentiments(
              (io.grpc.stub.StreamObserver<com.customersupport.sentiment.SentimentProto.SentimentResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SentimentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SentimentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.customersupport.sentiment.SentimentProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SentimentService");
    }
  }

  private static final class SentimentServiceFileDescriptorSupplier
      extends SentimentServiceBaseDescriptorSupplier {
    SentimentServiceFileDescriptorSupplier() {}
  }

  private static final class SentimentServiceMethodDescriptorSupplier
      extends SentimentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SentimentServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SentimentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SentimentServiceFileDescriptorSupplier())
              .addMethod(getAnalyzeSentimentsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
