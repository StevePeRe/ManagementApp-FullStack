package com.stevecoder.managementApp.task.domain.entity;

import com.stevecoder.managementApp.task.domain.enums.TaskState;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Task {

    private Long id;
    private String name;
    private TaskState state;
    private LocalDateTime createdAt; // momento de creacion
    private LocalDateTime startedAt; // momento de running

}
