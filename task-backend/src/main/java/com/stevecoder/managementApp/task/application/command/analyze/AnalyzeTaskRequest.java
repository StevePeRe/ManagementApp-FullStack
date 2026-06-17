package com.stevecoder.managementApp.task.application.command.analyze;

import com.stevecoder.managementApp.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyzeTaskRequest implements Request<AnalyzeTaskResponse> {
    private String description;
}
