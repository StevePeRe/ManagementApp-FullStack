package com.stevecoder.managementApp.task.infrastructure.database.entity;

import com.stevecoder.managementApp.task.domain.enums.TaskCategory;
import com.stevecoder.managementApp.task.domain.enums.TaskPriority;
import com.stevecoder.managementApp.task.domain.enums.TaskState;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TaskState state;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    private TaskCategory category;

    private LocalDate dueDate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime startedAt;

}
