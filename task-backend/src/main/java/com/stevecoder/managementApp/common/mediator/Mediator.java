package com.stevecoder.managementApp.common.mediator;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
//@Slf4j
public class Mediator {

    Map<? extends Class<?>, RequestHandler<?, ?>> requestHandlerMap;

    public Mediator(List<RequestHandler<?, ?>> requestHandlers) {
        requestHandlerMap = requestHandlers.stream().collect(Collectors.toMap(RequestHandler::getRequestType, Function.identity()));
    }

    public <R, T extends Request<R>> R dispatch(T request) {
        RequestHandler<T, R> handler = (RequestHandler<T, R>) requestHandlerMap.get(request.getClass()); // llega la request que necesitemos (crear, update,obtener datos, etc)
        if (handler == null) {
//            log.error("No handler found for request type: {}", request.getClass());
            throw new RuntimeException("No handler found for request type: " + request.getClass());
        }
        return handler.handle(request);
    }

    // Crea otro hilo para manejarlo sin que el usuario tenga que esperar demasiado tiempo
    @Async
    public <R, T extends Request<R>> void dispatchAsync(T request) {
        this.dispatch(request);
    }
}
