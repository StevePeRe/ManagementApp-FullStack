package com.stevecoder.managementApp.task.application.command.update;

import com.stevecoder.managementApp.common.mediator.Request;
import com.stevecoder.managementApp.task.domain.enums.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateTaskRequest implements Request<Void> {
    private Long id;
    private String name;
    private TaskState state;
}
