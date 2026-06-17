package com.stevecoder.managementApp.task.infrastructure.api;

import com.stevecoder.managementApp.common.mediator.Mediator;
import com.stevecoder.managementApp.task.application.command.analyze.AnalyzeTaskRequest;
import com.stevecoder.managementApp.task.application.command.analyze.AnalyzeTaskResponse;
import com.stevecoder.managementApp.task.application.command.analyzeAndCreate.AnalyzeAndCreateTaskRequest;
import com.stevecoder.managementApp.task.application.command.analyzeAndCreate.AnalyzeAndCreateTaskResponse;
import com.stevecoder.managementApp.task.infrastructure.api.dto.AnalyzeTaskDTO;
import com.stevecoder.managementApp.task.infrastructure.api.dto.TaskAnalysisDTO;
import com.stevecoder.managementApp.task.infrastructure.api.dto.TaskDTO;
import com.stevecoder.managementApp.task.infrastructure.api.mapper.TaskMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "AI Analysis", description = "Análisis de tareas con inteligencia artificial")
@SecurityRequirement(name = "bearerAuth")
public class TaskAiController {

    private final Mediator mediator;
    private final TaskMapper taskMapper;

    @PostMapping("/analyze")
    @Operation(summary = "Analizar tarea con IA", description = "Envía una descripción en lenguaje natural y la IA extrae título, prioridad, categoría y fecha límite")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Análisis completado"),
            @ApiResponse(responseCode = "400", description = "Descripción inválida"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "503", description = "Servicio de IA no disponible")
    })
    public ResponseEntity<TaskAnalysisDTO> analyzeTask(@RequestBody @Valid AnalyzeTaskDTO dto) {
        AnalyzeTaskResponse response = mediator.dispatch(new AnalyzeTaskRequest(dto.getDescription()));
        return ResponseEntity.ok(response.getAnalysis());
    }

    @PostMapping("/analyze-and-create")
    @Operation(summary = "Analizar y crear tarea con IA", description = "Analiza la descripción con IA y crea la tarea automáticamente en la base de datos con prioridad, categoría y fecha extraídas")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarea creada con análisis de IA"),
            @ApiResponse(responseCode = "400", description = "Descripción inválida"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "503", description = "Servicio de IA no disponible")
    })
    public ResponseEntity<TaskDTO> analyzeAndCreateTask(@RequestBody @Valid AnalyzeTaskDTO dto) {
        AnalyzeAndCreateTaskResponse response = mediator.dispatch(new AnalyzeAndCreateTaskRequest(dto.getDescription()));
        TaskDTO taskDTO = taskMapper.mapToTaskDTO(response.getTask());
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDTO);
    }
}
