package com.stevecoder.managementApp.user.application.command.authenticate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticateUserResponse {
    private String token;
    private String username;
    private String role;
}
