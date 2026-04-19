package com.stevecoder.managementApp.task.application.query.getAll;

import com.stevecoder.managementApp.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllTasksRequest implements Request<GetAllTasksResponse> {
}
