package com.stevecoder.managementApp.task.application.command.analyzeAndCreate;

import com.stevecoder.managementApp.common.mediator.RequestHandler;
import com.stevecoder.managementApp.task.application.service.TaskAiService;
import com.stevecoder.managementApp.task.domain.entity.Task;
import com.stevecoder.managementApp.task.domain.enums.TaskState;
import com.stevecoder.managementApp.task.domain.port.TaskRepository;
import com.stevecoder.managementApp.task.infrastructure.api.dto.TaskAnalysisDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyzeAndCreateTaskHandler implements RequestHandler<AnalyzeAndCreateTaskRequest, AnalyzeAndCreateTaskResponse> {

    private final TaskRepository taskRepository;
    private final TaskAiService taskAiService;

    @Override
    public AnalyzeAndCreateTaskResponse handle(AnalyzeAndCreateTaskRequest request) {
        TaskAnalysisDTO analysis = taskAiService.analyzeTask(request.getDescription());

        Task task = Task.builder()
                .name(analysis.getTitle() != null ? analysis.getTitle() : request.getDescription())
                .state(TaskState.CREATED)
                .priority(analysis.getPriority())
                .category(analysis.getCategory())
                .dueDate(analysis.getDueDate())
                .build();

        Task savedTask = taskRepository.create(task);
        log.info("Task created with AI analysis: id={}, priority={}, category={}", savedTask.getId(), savedTask.getPriority(), savedTask.getCategory());

        return new AnalyzeAndCreateTaskResponse(savedTask, true);
    }

    @Override
    public Class<AnalyzeAndCreateTaskRequest> getRequestType() {
        return AnalyzeAndCreateTaskRequest.class;
    }
}
