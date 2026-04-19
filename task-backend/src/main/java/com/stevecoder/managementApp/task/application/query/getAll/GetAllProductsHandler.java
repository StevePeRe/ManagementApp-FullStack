package com.stevecoder.managementApp.task.application.query.getAll;

import com.stevecoder.managementApp.common.mediator.RequestHandler;
import com.stevecoder.managementApp.task.domain.entity.Task;
import com.stevecoder.managementApp.task.domain.port.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // o componente. El servicio se transforma en Handle y Request
@RequiredArgsConstructor
public class GetAllProductsHandler implements RequestHandler<GetAllTasksRequest, GetAllTasksResponse> {

    private final TaskRepository taskRepository;

    @Override
    public GetAllTasksResponse handle(GetAllTasksRequest request) {

        List<Task> task = taskRepository.findAll();

        return new GetAllTasksResponse(task);
    }

    @Override
    public Class<GetAllTasksRequest> getRequestType() {
        return GetAllTasksRequest.class;
    }
}
