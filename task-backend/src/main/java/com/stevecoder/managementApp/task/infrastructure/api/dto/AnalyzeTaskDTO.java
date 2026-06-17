package com.stevecoder.managementApp.task.infrastructure.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Texto natural para analizar con IA")
public class AnalyzeTaskDTO {
    @NotBlank
    @Schema(description = "Descripción en lenguaje natural", example = "Reunión con cliente el viernes para ver presupuesto urgente")
    private String description;
}
