package com.stevecoder.managementApp.task.infrastructure.database;

import com.stevecoder.managementApp.task.domain.entity.Task;
import com.stevecoder.managementApp.task.domain.enums.TaskState;
import com.stevecoder.managementApp.task.domain.exception.TaskNotFoundException;
import com.stevecoder.managementApp.task.domain.port.TaskRepository;
import com.stevecoder.managementApp.task.infrastructure.database.entity.TaskEntity;
import com.stevecoder.managementApp.task.infrastructure.database.mapper.TaskEntityMapper;
import com.stevecoder.managementApp.task.infrastructure.database.repository.QueryTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TaskRepositoryImpl implements TaskRepository {

    private final QueryTaskRepository repository;
    private final TaskEntityMapper taskEntityMapper;

    @Override
    public Task create(Task task) {

        TaskEntity taskEntity = taskEntityMapper.mapToTaskEntity(task);
        TaskEntity save = repository.save(taskEntity);

        return taskEntityMapper.mapToTask(save);

    }

    @Override
    public Task update(Long id, Task task) {
        TaskEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(new TaskNotFoundException(id)));

        entity.setName(task.getName());
        entity.setState(task.getState());

        return taskEntityMapper.mapToTask(repository.save(entity));
    }

//    @Cacheable(value = "products", key = "#id")
    @Override
    public Optional<Task> findById(Long id) {
        log.info("Finding task with id {}", id);
        return repository.findById(id).map(taskEntityMapper::mapToTask);
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll().stream().map(taskEntityMapper::mapToTask).toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // Tarea a estado RUNNING despues de 2min de ser creada
    @Override
    public int moveToRunning(LocalDateTime createdLimit, LocalDateTime now) {
        return repository.moveToRunning(
                createdLimit,
                now,
                TaskState.CREATED,
                TaskState.RUNNING
        );
    }

    // Tarea a estado DONE despues de 8min de pasar a RUNNING
    @Override
    public int moveToDone(LocalDateTime runningLimit) {
        return repository.moveToDone(
                runningLimit,
                TaskState.RUNNING,
                TaskState.DONE
        );
    }
}
