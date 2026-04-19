package com.stevecoder.managementApp.task.infrastructure.api;

import com.stevecoder.managementApp.task.infrastructure.api.dto.TaskDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TaskApi {

    public ResponseEntity<Void> createTask(@RequestBody TaskDTO taskDTO);

    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDTO taskDTO);

    public ResponseEntity<Void> deleteTask(@PathVariable Long id);

    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id);

    public ResponseEntity<List<TaskDTO>> getAllTasks(@RequestParam(required = false) String pageSize);

}
