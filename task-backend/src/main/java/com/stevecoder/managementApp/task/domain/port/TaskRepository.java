package com.stevecoder.managementApp.task.domain.port;

import com.stevecoder.managementApp.task.domain.entity.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task create(Task task);

    Task update(Long id, Task task);

    Optional<Task> findById(Long id);

    List<Task> findAll();

    void deleteById(Long id);

    int moveToRunning(LocalDateTime createdLimit, LocalDateTime now);

    int moveToDone(LocalDateTime runningLimit);
}
