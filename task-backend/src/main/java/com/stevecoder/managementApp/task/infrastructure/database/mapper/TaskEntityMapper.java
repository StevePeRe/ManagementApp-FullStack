package com.stevecoder.managementApp.task.infrastructure.database.mapper;

import com.stevecoder.managementApp.task.domain.entity.Task;
import com.stevecoder.managementApp.task.infrastructure.database.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface TaskEntityMapper {

    TaskEntity mapToTaskEntity(Task task);

    Task mapToTask(TaskEntity taskEntity);
}
