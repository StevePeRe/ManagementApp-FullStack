package com.stevecoder.managementApp.task.application.command.analyze;

import com.stevecoder.managementApp.task.infrastructure.api.dto.TaskAnalysisDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AnalyzeTaskResponse {
    private TaskAnalysisDTO analysis;
}
