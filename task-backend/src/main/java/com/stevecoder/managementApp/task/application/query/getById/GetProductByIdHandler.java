package com.stevecoder.managementApp.task.application.query.getById;

import com.stevecoder.managementApp.common.mediator.RequestHandler;
import com.stevecoder.managementApp.task.domain.entity.Task;
import com.stevecoder.managementApp.task.domain.exception.TaskNotFoundException;
import com.stevecoder.managementApp.task.domain.port.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // o componente. El servicio se transforma en Handle y Request
@RequiredArgsConstructor
public class GetProductByIdHandler implements RequestHandler<GetTaskByIdRequest, GetTaskByIdResponse> {

    private final TaskRepository taskRepository;

    @Override
    public GetTaskByIdResponse handle(GetTaskByIdRequest request) {

        Task task = taskRepository.findById(request.getId()).orElseThrow(() -> new TaskNotFoundException(request.getId()));

        return new GetTaskByIdResponse(task);
    }

    @Override
    public Class<GetTaskByIdRequest> getRequestType() {
        return GetTaskByIdRequest.class;
    }
}
