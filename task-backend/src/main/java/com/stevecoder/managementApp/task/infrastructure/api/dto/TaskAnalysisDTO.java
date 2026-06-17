package com.stevecoder.managementApp.task.infrastructure.api.dto;

import com.stevecoder.managementApp.task.domain.enums.TaskCategory;
import com.stevecoder.managementApp.task.domain.enums.TaskPriority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Resultado del análisis de IA sobre una tarea")
public class TaskAnalysisDTO {

    @Schema(description = "Título extraído", example = "Reunión con cliente")
    private String title;

    @Schema(description = "Prioridad inferida", example = "URGENT")
    private TaskPriority priority;

    @Schema(description = "Categoría inferida", example = "WORK")
    private TaskCategory category;

    @Schema(description = "Fecha límite inferida", example = "2025-06-13")
    private LocalDate dueDate;

    @Schema(description = "Confianza del análisis (0-1)", example = "0.92")
    private Double confidence;
}
