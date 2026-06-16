package com.stevecoder.managementApp.user.application.command.register;

import com.stevecoder.managementApp.common.mediator.Request;
import com.stevecoder.managementApp.user.domain.enums.Role;
import lombok.Data;

@Data
public class RegisterUserRequest implements Request<RegisterUserResponse> {
    private String username;
    private String password;
    private String email;
    private Role role;
}
