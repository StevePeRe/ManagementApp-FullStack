package com.stevecoder.managementApp.user.application.command.register;

import com.stevecoder.managementApp.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterUserResponse {
    private User user;
}
