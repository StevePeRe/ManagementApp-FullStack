package com.stevecoder.managementApp.task.infrastructure.api;

import com.stevecoder.managementApp.task.infrastructure.api.dto.TaskDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Tasks", description = "Endpoints para gestión de tareas")
@SecurityRequirement(name = "bearerAuth")
public interface TaskApi {

    @Operation(summary = "Crear una nueva tarea", description = "Crea una tarea con nombre y estado opcional")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarea creada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    ResponseEntity<Void> createTask(@RequestBody @Valid TaskDTO taskDTO);

    @Operation(summary = "Actualizar una tarea", description = "Actualiza nombre y/o estado de una tarea existente")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Tarea actualizada"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    ResponseEntity<Void> updateTask(
            @Parameter(description = "ID de la tarea") @PathVariable Long id,
            @RequestBody @Valid TaskDTO taskDTO);

    @Operation(summary = "Eliminar una tarea", description = "Elimina una tarea de forma asíncrona")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Eliminación en proceso"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    ResponseEntity<Void> deleteTask(@Parameter(description = "ID de la tarea") @PathVariable Long id);

    @Operation(summary = "Obtener tarea por ID", description = "Retorna los detalles de una tarea específica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    ResponseEntity<TaskDTO> getTaskById(@Parameter(description = "ID de la tarea") @PathVariable Long id);

    @Operation(summary = "Obtener todas las tareas", description = "Lista todas las tareas con paginación opcional")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de tareas")
    })
    ResponseEntity<List<TaskDTO>> getAllTasks(
            @Parameter(description = "Tamaño de página") @RequestParam(required = false) String pageSize);
}
