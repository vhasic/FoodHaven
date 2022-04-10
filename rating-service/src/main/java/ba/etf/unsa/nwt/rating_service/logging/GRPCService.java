package ba.etf.unsa.nwt.rating_service.logging;

import ba.unsa.etf.nwt.system_events_service.grpc.ActionsRequest;
import ba.unsa.etf.nwt.system_events_service.grpc.ActionsResponse;
import ba.unsa.etf.nwt.system_events_service.grpc.ActionsServiceGrpc;
import com.google.protobuf.Timestamp;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Service
@RequiredArgsConstructor
//@RefreshScope
public class GRPCService {
    private final EurekaClient eurekaClient;
/*    private ActionsServiceGrpc.ActionsServiceStub actionsServiceStub;

    @PostConstruct
    private void createServiceStub() {
        createStub();
    }
    private boolean createStub() {
        try {
            InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("system-events-service", false);
            ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(),
                    9090).usePlaintext().build();
            actionsServiceStub = ActionsServiceGrpc.newStub(channel);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }*/

    public void save(String actionType, String resourceName, String responseType, String username) {
        try {
/*            boolean stubCreated = actionsServiceStub != null;
            if (!stubCreated) stubCreated = createStub();
            if(stubCreated) {
                Instant now = Instant.now();
                StreamObserver<ActionsResponse> responseObserver = new StreamObserver<>() {
                    @Override
                    public void onNext(ActionsResponse actionsResponse) {
                        System.out.println("gRPC response with status: " + actionsResponse.getStatus());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Completed gRPC call");
                    }
                };

                actionsServiceStub.save(ActionsRequest.newBuilder()
                        .setTimestamp(Timestamp.newBuilder().setSeconds(now.getEpochSecond()).setNanos(now.getNano()).build())
                        .setService("rating-service")
                        .setActionType(actionType)
                        .setResourceName(resourceName)
                        .setResponseType(responseType)
                        .setUsername(username)
                        .build(), responseObserver);
            }*/
            //            ActionsServiceGrpc.ActionsServiceBlockingStub stub = ActionsServiceGrpc.newBlockingStub(channel);
            Instant now = Instant.now();
            InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("system-events-service", false);
            ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), 9090)
                    .usePlaintext()
                    .build();
            ActionsServiceGrpc.ActionsServiceBlockingStub stub = ActionsServiceGrpc.newBlockingStub(channel);
            ActionsResponse response = stub.save(ActionsRequest.newBuilder()
                    .setTimestamp(Timestamp.newBuilder().setSeconds(now.getEpochSecond()).setNanos(now.getNano()).build())
                    .setService("rating-service")
                    .setActionType(actionType)
                    .setResourceName(resourceName)
                    .setResponseType(responseType)
                    .setUsername(username)
                    .build());
            channel.shutdown();
        } catch ( Exception e ) {
            System.out.println("Can't connect to system-events-service to store action!");
            e.printStackTrace();
        }
    }
}
