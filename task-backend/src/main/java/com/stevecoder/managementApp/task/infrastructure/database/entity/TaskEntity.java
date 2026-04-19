package com.stevecoder.managementApp.task.infrastructure.database.entity;

import com.stevecoder.managementApp.task.domain.enums.TaskState;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt; // momento de creacion
    private LocalDateTime startedAt; // momento de running

}
