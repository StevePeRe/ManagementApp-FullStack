package com.stevecoder.managementApp.task.application.query.getAll;

import com.stevecoder.managementApp.task.domain.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GetAllTasksResponse {
    private List<Task> task;
}
