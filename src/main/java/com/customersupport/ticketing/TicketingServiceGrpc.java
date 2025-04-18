package com.customersupport.ticketing;

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
    comments = "Source: ticketing.proto")
public final class TicketingServiceGrpc {

  private TicketingServiceGrpc() {}

  public static final String SERVICE_NAME = "TicketingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketRequest,
      com.customersupport.ticketing.TicketingProto.TicketResponse> getCreateTicketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTicket",
      requestType = com.customersupport.ticketing.TicketingProto.TicketRequest.class,
      responseType = com.customersupport.ticketing.TicketingProto.TicketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketRequest,
      com.customersupport.ticketing.TicketingProto.TicketResponse> getCreateTicketMethod() {
    io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketRequest, com.customersupport.ticketing.TicketingProto.TicketResponse> getCreateTicketMethod;
    if ((getCreateTicketMethod = TicketingServiceGrpc.getCreateTicketMethod) == null) {
      synchronized (TicketingServiceGrpc.class) {
        if ((getCreateTicketMethod = TicketingServiceGrpc.getCreateTicketMethod) == null) {
          TicketingServiceGrpc.getCreateTicketMethod = getCreateTicketMethod = 
              io.grpc.MethodDescriptor.<com.customersupport.ticketing.TicketingProto.TicketRequest, com.customersupport.ticketing.TicketingProto.TicketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TicketingService", "CreateTicket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.ticketing.TicketingProto.TicketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.ticketing.TicketingProto.TicketResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketingServiceMethodDescriptorSupplier("CreateTicket"))
                  .build();
          }
        }
     }
     return getCreateTicketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketStatusRequest,
      com.customersupport.ticketing.TicketingProto.TicketStatusResponse> getGetTicketStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTicketStatus",
      requestType = com.customersupport.ticketing.TicketingProto.TicketStatusRequest.class,
      responseType = com.customersupport.ticketing.TicketingProto.TicketStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketStatusRequest,
      com.customersupport.ticketing.TicketingProto.TicketStatusResponse> getGetTicketStatusMethod() {
    io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketStatusRequest, com.customersupport.ticketing.TicketingProto.TicketStatusResponse> getGetTicketStatusMethod;
    if ((getGetTicketStatusMethod = TicketingServiceGrpc.getGetTicketStatusMethod) == null) {
      synchronized (TicketingServiceGrpc.class) {
        if ((getGetTicketStatusMethod = TicketingServiceGrpc.getGetTicketStatusMethod) == null) {
          TicketingServiceGrpc.getGetTicketStatusMethod = getGetTicketStatusMethod = 
              io.grpc.MethodDescriptor.<com.customersupport.ticketing.TicketingProto.TicketStatusRequest, com.customersupport.ticketing.TicketingProto.TicketStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TicketingService", "GetTicketStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.ticketing.TicketingProto.TicketStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.ticketing.TicketingProto.TicketStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketingServiceMethodDescriptorSupplier("GetTicketStatus"))
                  .build();
          }
        }
     }
     return getGetTicketStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketRequest,
      com.customersupport.ticketing.TicketingProto.TicketResponse> getCreateTicketStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTicketStream",
      requestType = com.customersupport.ticketing.TicketingProto.TicketRequest.class,
      responseType = com.customersupport.ticketing.TicketingProto.TicketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketRequest,
      com.customersupport.ticketing.TicketingProto.TicketResponse> getCreateTicketStreamMethod() {
    io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketRequest, com.customersupport.ticketing.TicketingProto.TicketResponse> getCreateTicketStreamMethod;
    if ((getCreateTicketStreamMethod = TicketingServiceGrpc.getCreateTicketStreamMethod) == null) {
      synchronized (TicketingServiceGrpc.class) {
        if ((getCreateTicketStreamMethod = TicketingServiceGrpc.getCreateTicketStreamMethod) == null) {
          TicketingServiceGrpc.getCreateTicketStreamMethod = getCreateTicketStreamMethod = 
              io.grpc.MethodDescriptor.<com.customersupport.ticketing.TicketingProto.TicketRequest, com.customersupport.ticketing.TicketingProto.TicketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "TicketingService", "CreateTicketStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.ticketing.TicketingProto.TicketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.ticketing.TicketingProto.TicketResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketingServiceMethodDescriptorSupplier("CreateTicketStream"))
                  .build();
          }
        }
     }
     return getCreateTicketStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketStatusRequest,
      com.customersupport.ticketing.TicketingProto.TicketStatusBatchResponse> getCheckMultipleStatusesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckMultipleStatuses",
      requestType = com.customersupport.ticketing.TicketingProto.TicketStatusRequest.class,
      responseType = com.customersupport.ticketing.TicketingProto.TicketStatusBatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketStatusRequest,
      com.customersupport.ticketing.TicketingProto.TicketStatusBatchResponse> getCheckMultipleStatusesMethod() {
    io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketStatusRequest, com.customersupport.ticketing.TicketingProto.TicketStatusBatchResponse> getCheckMultipleStatusesMethod;
    if ((getCheckMultipleStatusesMethod = TicketingServiceGrpc.getCheckMultipleStatusesMethod) == null) {
      synchronized (TicketingServiceGrpc.class) {
        if ((getCheckMultipleStatusesMethod = TicketingServiceGrpc.getCheckMultipleStatusesMethod) == null) {
          TicketingServiceGrpc.getCheckMultipleStatusesMethod = getCheckMultipleStatusesMethod = 
              io.grpc.MethodDescriptor.<com.customersupport.ticketing.TicketingProto.TicketStatusRequest, com.customersupport.ticketing.TicketingProto.TicketStatusBatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "TicketingService", "CheckMultipleStatuses"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.ticketing.TicketingProto.TicketStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.ticketing.TicketingProto.TicketStatusBatchResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketingServiceMethodDescriptorSupplier("CheckMultipleStatuses"))
                  .build();
          }
        }
     }
     return getCheckMultipleStatusesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketCloseRequest,
      com.customersupport.ticketing.TicketingProto.TicketCloseResponse> getCloseTicketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CloseTicket",
      requestType = com.customersupport.ticketing.TicketingProto.TicketCloseRequest.class,
      responseType = com.customersupport.ticketing.TicketingProto.TicketCloseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketCloseRequest,
      com.customersupport.ticketing.TicketingProto.TicketCloseResponse> getCloseTicketMethod() {
    io.grpc.MethodDescriptor<com.customersupport.ticketing.TicketingProto.TicketCloseRequest, com.customersupport.ticketing.TicketingProto.TicketCloseResponse> getCloseTicketMethod;
    if ((getCloseTicketMethod = TicketingServiceGrpc.getCloseTicketMethod) == null) {
      synchronized (TicketingServiceGrpc.class) {
        if ((getCloseTicketMethod = TicketingServiceGrpc.getCloseTicketMethod) == null) {
          TicketingServiceGrpc.getCloseTicketMethod = getCloseTicketMethod = 
              io.grpc.MethodDescriptor.<com.customersupport.ticketing.TicketingProto.TicketCloseRequest, com.customersupport.ticketing.TicketingProto.TicketCloseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TicketingService", "CloseTicket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.ticketing.TicketingProto.TicketCloseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customersupport.ticketing.TicketingProto.TicketCloseResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketingServiceMethodDescriptorSupplier("CloseTicket"))
                  .build();
          }
        }
     }
     return getCloseTicketMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TicketingServiceStub newStub(io.grpc.Channel channel) {
    return new TicketingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TicketingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TicketingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TicketingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TicketingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TicketingServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * RPC simple
     * </pre>
     */
    public void createTicket(com.customersupport.ticketing.TicketingProto.TicketRequest request,
        io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateTicketMethod(), responseObserver);
    }

    /**
     */
    public void getTicketStatus(com.customersupport.ticketing.TicketingProto.TicketStatusRequest request,
        io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTicketStatusMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server Streaming
     * </pre>
     */
    public void createTicketStream(com.customersupport.ticketing.TicketingProto.TicketRequest request,
        io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateTicketStreamMethod(), responseObserver);
    }

    /**
     * <pre>
     * Client Streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketStatusRequest> checkMultipleStatuses(
        io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketStatusBatchResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getCheckMultipleStatusesMethod(), responseObserver);
    }

    /**
     * <pre>
     *Close ticket
     * </pre>
     */
    public void closeTicket(com.customersupport.ticketing.TicketingProto.TicketCloseRequest request,
        io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketCloseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCloseTicketMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateTicketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.customersupport.ticketing.TicketingProto.TicketRequest,
                com.customersupport.ticketing.TicketingProto.TicketResponse>(
                  this, METHODID_CREATE_TICKET)))
          .addMethod(
            getGetTicketStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.customersupport.ticketing.TicketingProto.TicketStatusRequest,
                com.customersupport.ticketing.TicketingProto.TicketStatusResponse>(
                  this, METHODID_GET_TICKET_STATUS)))
          .addMethod(
            getCreateTicketStreamMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.customersupport.ticketing.TicketingProto.TicketRequest,
                com.customersupport.ticketing.TicketingProto.TicketResponse>(
                  this, METHODID_CREATE_TICKET_STREAM)))
          .addMethod(
            getCheckMultipleStatusesMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.customersupport.ticketing.TicketingProto.TicketStatusRequest,
                com.customersupport.ticketing.TicketingProto.TicketStatusBatchResponse>(
                  this, METHODID_CHECK_MULTIPLE_STATUSES)))
          .addMethod(
            getCloseTicketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.customersupport.ticketing.TicketingProto.TicketCloseRequest,
                com.customersupport.ticketing.TicketingProto.TicketCloseResponse>(
                  this, METHODID_CLOSE_TICKET)))
          .build();
    }
  }

  /**
   */
  public static final class TicketingServiceStub extends io.grpc.stub.AbstractStub<TicketingServiceStub> {
    private TicketingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketingServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * RPC simple
     * </pre>
     */
    public void createTicket(com.customersupport.ticketing.TicketingProto.TicketRequest request,
        io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateTicketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTicketStatus(com.customersupport.ticketing.TicketingProto.TicketStatusRequest request,
        io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTicketStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server Streaming
     * </pre>
     */
    public void createTicketStream(com.customersupport.ticketing.TicketingProto.TicketRequest request,
        io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getCreateTicketStreamMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Client Streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketStatusRequest> checkMultipleStatuses(
        io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketStatusBatchResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getCheckMultipleStatusesMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *Close ticket
     * </pre>
     */
    public void closeTicket(com.customersupport.ticketing.TicketingProto.TicketCloseRequest request,
        io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketCloseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCloseTicketMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TicketingServiceBlockingStub extends io.grpc.stub.AbstractStub<TicketingServiceBlockingStub> {
    private TicketingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketingServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * RPC simple
     * </pre>
     */
    public com.customersupport.ticketing.TicketingProto.TicketResponse createTicket(com.customersupport.ticketing.TicketingProto.TicketRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateTicketMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.customersupport.ticketing.TicketingProto.TicketStatusResponse getTicketStatus(com.customersupport.ticketing.TicketingProto.TicketStatusRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetTicketStatusMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server Streaming
     * </pre>
     */
    public java.util.Iterator<com.customersupport.ticketing.TicketingProto.TicketResponse> createTicketStream(
        com.customersupport.ticketing.TicketingProto.TicketRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getCreateTicketStreamMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Close ticket
     * </pre>
     */
    public com.customersupport.ticketing.TicketingProto.TicketCloseResponse closeTicket(com.customersupport.ticketing.TicketingProto.TicketCloseRequest request) {
      return blockingUnaryCall(
          getChannel(), getCloseTicketMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TicketingServiceFutureStub extends io.grpc.stub.AbstractStub<TicketingServiceFutureStub> {
    private TicketingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketingServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * RPC simple
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.customersupport.ticketing.TicketingProto.TicketResponse> createTicket(
        com.customersupport.ticketing.TicketingProto.TicketRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateTicketMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.customersupport.ticketing.TicketingProto.TicketStatusResponse> getTicketStatus(
        com.customersupport.ticketing.TicketingProto.TicketStatusRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTicketStatusMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Close ticket
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.customersupport.ticketing.TicketingProto.TicketCloseResponse> closeTicket(
        com.customersupport.ticketing.TicketingProto.TicketCloseRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCloseTicketMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_TICKET = 0;
  private static final int METHODID_GET_TICKET_STATUS = 1;
  private static final int METHODID_CREATE_TICKET_STREAM = 2;
  private static final int METHODID_CLOSE_TICKET = 3;
  private static final int METHODID_CHECK_MULTIPLE_STATUSES = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TicketingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TicketingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_TICKET:
          serviceImpl.createTicket((com.customersupport.ticketing.TicketingProto.TicketRequest) request,
              (io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketResponse>) responseObserver);
          break;
        case METHODID_GET_TICKET_STATUS:
          serviceImpl.getTicketStatus((com.customersupport.ticketing.TicketingProto.TicketStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketStatusResponse>) responseObserver);
          break;
        case METHODID_CREATE_TICKET_STREAM:
          serviceImpl.createTicketStream((com.customersupport.ticketing.TicketingProto.TicketRequest) request,
              (io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketResponse>) responseObserver);
          break;
        case METHODID_CLOSE_TICKET:
          serviceImpl.closeTicket((com.customersupport.ticketing.TicketingProto.TicketCloseRequest) request,
              (io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketCloseResponse>) responseObserver);
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
        case METHODID_CHECK_MULTIPLE_STATUSES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.checkMultipleStatuses(
              (io.grpc.stub.StreamObserver<com.customersupport.ticketing.TicketingProto.TicketStatusBatchResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TicketingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TicketingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.customersupport.ticketing.TicketingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TicketingService");
    }
  }

  private static final class TicketingServiceFileDescriptorSupplier
      extends TicketingServiceBaseDescriptorSupplier {
    TicketingServiceFileDescriptorSupplier() {}
  }

  private static final class TicketingServiceMethodDescriptorSupplier
      extends TicketingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TicketingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TicketingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TicketingServiceFileDescriptorSupplier())
              .addMethod(getCreateTicketMethod())
              .addMethod(getGetTicketStatusMethod())
              .addMethod(getCreateTicketStreamMethod())
              .addMethod(getCheckMultipleStatusesMethod())
              .addMethod(getCloseTicketMethod())
              .build();
        }
      }
    }
    return result;
  }
}
