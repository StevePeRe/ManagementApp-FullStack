package com.stevecoder.managementApp.task.infrastructure.api.mapper;

import com.stevecoder.managementApp.task.application.command.create.CreateTaskRequest;
import com.stevecoder.managementApp.task.application.command.update.UpdateTaskRequest;
import com.stevecoder.managementApp.task.domain.entity.Task;
import com.stevecoder.managementApp.task.infrastructure.api.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    CreateTaskRequest mapToCreateTaskRequest(TaskDTO taskDTO);

    UpdateTaskRequest mapToUpdateTaskRequest(TaskDTO taskDTO);

    TaskDTO mapToTaskDTO(Task task);
}
