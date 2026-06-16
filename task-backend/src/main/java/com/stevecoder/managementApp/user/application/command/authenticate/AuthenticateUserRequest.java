package com.stevecoder.managementApp.user.application.command.authenticate;

import com.stevecoder.managementApp.common.mediator.Request;
import lombok.Data;

@Data
public class AuthenticateUserRequest implements Request<AuthenticateUserResponse> {
    private String username;
    private String password;
}
