package com.stevecoder.managementApp.task.application.command.delete;

import com.stevecoder.managementApp.common.mediator.RequestHandler;
import com.stevecoder.managementApp.task.domain.port.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service // o componente. El servicio se transforma en Handle y Request
@RequiredArgsConstructor
@Slf4j
public class DeleteTaskHandler implements RequestHandler<DeleteTaskRequest, Void> {

    private final TaskRepository taskRepository;

    @Override
    public Void handle(DeleteTaskRequest request) {

        taskRepository.deleteById(request.getId());

        // las validaciones en la infraestructura
        return null;
    }

    @Override
    public Class<DeleteTaskRequest> getRequestType() {
        return DeleteTaskRequest.class;
    }
}
