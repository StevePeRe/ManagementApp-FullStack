package com.stevecoder.managementApp.task.application.command.delete;

import com.stevecoder.managementApp.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTaskRequest implements Request<Void> {
    private Long id;
}
