package com.stevecoder.managementApp.task.infrastructure.api;

import com.stevecoder.managementApp.common.mediator.Mediator;
import com.stevecoder.managementApp.task.application.command.create.CreateTaskResponse;
import com.stevecoder.managementApp.task.application.command.delete.DeleteTaskRequest;
import com.stevecoder.managementApp.task.application.command.update.UpdateTaskRequest;
import com.stevecoder.managementApp.task.application.query.getAll.GetAllTasksRequest;
import com.stevecoder.managementApp.task.application.query.getAll.GetAllTasksResponse;
import com.stevecoder.managementApp.task.application.query.getById.GetTaskByIdRequest;
import com.stevecoder.managementApp.task.application.query.getById.GetTaskByIdResponse;
import com.stevecoder.managementApp.task.domain.entity.Task;
import com.stevecoder.managementApp.task.infrastructure.api.dto.TaskDTO;
import com.stevecoder.managementApp.task.infrastructure.api.mapper.TaskMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController implements TaskApi {

    private final Mediator mediator;
    private final TaskMapper taskMapper;

    @GetMapping("")
    @Override
    public ResponseEntity<List<TaskDTO>> getAllTasks(@RequestParam(required = false) String pageSize) {

        GetAllTasksResponse response = mediator.dispatch(new GetAllTasksRequest());
        List<TaskDTO> taskDTOList = response.getTask().stream().map(taskMapper::mapToTaskDTO).toList();

        return ResponseEntity.ok(taskDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {

        GetTaskByIdResponse response = mediator.dispatch(new GetTaskByIdRequest(id));
        TaskDTO taskDTO = taskMapper.mapToTaskDTO(response.getTask());

        return ResponseEntity.ok(taskDTO);
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody @Valid TaskDTO taskDTO) {

        CreateTaskResponse response = mediator.dispatch(taskMapper.mapToCreateTaskRequest(taskDTO));
        Task task = response.getTask();
        log.info("Task created with id {}", task.getId());

        return ResponseEntity.created(URI.create("/api/tasks".concat(task.getId().toString()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDTO taskDTO) {

        mediator.dispatch(new UpdateTaskRequest(id, taskDTO.getName(), taskDTO.getState()));
        log.info("Task updated with id {}", id);

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        mediator.dispatchAsync(new DeleteTaskRequest(id));

        return ResponseEntity.accepted().build();
    }
}
