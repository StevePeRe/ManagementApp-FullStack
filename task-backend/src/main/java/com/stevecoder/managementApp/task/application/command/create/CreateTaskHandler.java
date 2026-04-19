package com.stevecoder.managementApp.task.application.command.create;

import com.stevecoder.managementApp.common.mediator.RequestHandler;
import com.stevecoder.managementApp.task.domain.entity.Task;
import com.stevecoder.managementApp.task.domain.port.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // o componente. El servicio se transforma en Handle y Request
@RequiredArgsConstructor
public class CreateTaskHandler implements RequestHandler<CreateTaskRequest, CreateTaskResponse> {

    private final TaskRepository taskRepository;

    @Override
    public CreateTaskResponse handle(CreateTaskRequest request) {

        Task task = Task.builder()
                .name(request.getName())
                .state(request.getState())
                .build();

        Task storedTask = taskRepository.create(task);
        
        return new CreateTaskResponse(storedTask);
    }

    @Override
    public Class<CreateTaskRequest> getRequestType() {
        return CreateTaskRequest.class;
    }
}
