package com.stevecoder.managementApp.task.application.command.create;

import com.stevecoder.managementApp.task.domain.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateTaskResponse {
    private Task task;
}
