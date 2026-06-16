package com.stevecoder.managementApp.task.infrastructure.api.dto;

import com.stevecoder.managementApp.task.domain.enums.TaskState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Representación de una tarea")
public class TaskDTO {

    @Schema(description = "ID de la tarea", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Nombre de la tarea", example = "Revisar documentación del proyecto")
    private String name;

    @Schema(description = "Estado de la tarea", example = "CREATED", allowableValues = {"CREATED", "RUNNING", "DONE"})
    private TaskState state;

    @Schema(description = "Fecha de creación")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de inicio (estado RUNNING)")
    private LocalDateTime startedAt;
}
