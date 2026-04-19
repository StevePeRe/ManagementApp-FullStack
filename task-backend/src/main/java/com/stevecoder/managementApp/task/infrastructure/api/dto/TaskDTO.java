package com.stevecoder.managementApp.task.infrastructure.api.dto;

import com.stevecoder.managementApp.task.domain.enums.TaskState;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {

    private Long id;
    @NotBlank
    private String name;
    private TaskState state;
    private LocalDateTime createdAt; // momento de creacion
    private LocalDateTime startedAt; // momento de running
}
