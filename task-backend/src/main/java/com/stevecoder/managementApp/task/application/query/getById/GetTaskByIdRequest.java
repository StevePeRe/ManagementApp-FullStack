package com.stevecoder.managementApp.task.application.query.getById;

import com.stevecoder.managementApp.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetTaskByIdRequest implements Request<GetTaskByIdResponse> {
    private Long id;
}
