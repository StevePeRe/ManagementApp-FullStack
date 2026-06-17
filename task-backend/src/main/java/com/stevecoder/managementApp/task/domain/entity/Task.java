package com.stevecoder.managementApp.task.domain.entity;

import com.stevecoder.managementApp.task.domain.enums.TaskCategory;
import com.stevecoder.managementApp.task.domain.enums.TaskPriority;
import com.stevecoder.managementApp.task.domain.enums.TaskState;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class Task {

    private Long id;
    private String name;
    private TaskState state;
    private TaskPriority priority;
    private TaskCategory category;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;

}
