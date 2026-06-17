package com.stevecoder.managementApp.task.application.command.analyze;

import com.stevecoder.managementApp.common.mediator.RequestHandler;
import com.stevecoder.managementApp.task.application.service.TaskAiService;
import com.stevecoder.managementApp.task.infrastructure.api.dto.TaskAnalysisDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyzeTaskHandler implements RequestHandler<AnalyzeTaskRequest, AnalyzeTaskResponse> {

    private final TaskAiService taskAiService;

    @Override
    public AnalyzeTaskResponse handle(AnalyzeTaskRequest request) {
        TaskAnalysisDTO analysis = taskAiService.analyzeTask(request.getDescription());
        return new AnalyzeTaskResponse(analysis);
    }

    @Override
    public Class<AnalyzeTaskRequest> getRequestType() {
        return AnalyzeTaskRequest.class;
    }
}
