package com.stevecoder.managementApp.task.application.command.analyzeAndCreate;

import com.stevecoder.managementApp.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyzeAndCreateTaskRequest implements Request<AnalyzeAndCreateTaskResponse> {
    private String description;
}
