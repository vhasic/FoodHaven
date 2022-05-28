package ba.unsa.etf.nwt.system_events_service.service;

import ba.unsa.etf.nwt.system_events_service.domain.Action;
import ba.unsa.etf.nwt.system_events_service.grpc.ActionsRequest;
import ba.unsa.etf.nwt.system_events_service.grpc.ActionsResponse;
import ba.unsa.etf.nwt.system_events_service.grpc.ActionsServiceGrpc;
import ba.unsa.etf.nwt.system_events_service.repos.ActionRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;


@GrpcService
public class ActionService extends ActionsServiceGrpc.ActionsServiceImplBase {

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public void save(ActionsRequest request, StreamObserver<ActionsResponse> responseObserver){
        Action action = new Action();
        action.setTimestamp(Instant.ofEpochSecond(request.getTimestamp().getSeconds(), request.getTimestamp().getNanos()));
        action.setService(request.getService());
        action.setActionType(request.getActionType());
        action.setResourceName(request.getResourceName());
        action.setResponseType(request.getResponseType());
        action.setUsername(request.getUsername());
        actionRepository.save(action);

        ActionsResponse response = ActionsResponse.newBuilder()
                .setStatus("OK")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
