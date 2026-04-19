package com.stevecoder.managementApp.task.application.command.update;

import com.stevecoder.managementApp.common.mediator.RequestHandler;
import com.stevecoder.managementApp.task.domain.entity.Task;
import com.stevecoder.managementApp.task.domain.port.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service // o componente. El servicio se transforma en Handle y Request
@RequiredArgsConstructor
@Slf4j
public class UpdateTaskHandler implements RequestHandler<UpdateTaskRequest, Void> {

    private final TaskRepository taskRepository;

    @Override
    public Void handle(UpdateTaskRequest request) {

        Task task = Task.builder()
                .name(request.getName())
                .state(request.getState())
                .build();

        taskRepository.update(request.getId(), task);

        return null;
    }

    @Override
    public Class<UpdateTaskRequest> getRequestType() {
        return UpdateTaskRequest.class;
    }
}
