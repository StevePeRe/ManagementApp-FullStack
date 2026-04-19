package com.stevecoder.managementApp.task.application.query.getById;

import com.stevecoder.managementApp.task.domain.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetTaskByIdResponse {
    private Task task;
}
