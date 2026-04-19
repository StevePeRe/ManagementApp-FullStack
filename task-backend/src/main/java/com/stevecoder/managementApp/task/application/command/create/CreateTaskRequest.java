package com.stevecoder.managementApp.task.application.command.create;

import com.stevecoder.managementApp.common.mediator.Request;
import com.stevecoder.managementApp.task.domain.enums.TaskState;
import lombok.Data;

@Data
public class CreateTaskRequest implements Request<CreateTaskResponse> {
    private String name;
    private TaskState state;
}
