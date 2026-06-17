package com.stevecoder.managementApp.task.application.command.analyzeAndCreate;

import com.stevecoder.managementApp.task.domain.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AnalyzeAndCreateTaskResponse {
    private Task task;
    private boolean aiAnalyzed;
}
