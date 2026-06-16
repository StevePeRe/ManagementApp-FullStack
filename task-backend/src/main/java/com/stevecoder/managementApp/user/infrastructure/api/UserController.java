package com.stevecoder.managementApp.user.infrastructure.api;

import com.stevecoder.managementApp.common.mediator.Mediator;
import com.stevecoder.managementApp.user.application.command.authenticate.AuthenticateUserResponse;
import com.stevecoder.managementApp.user.application.command.register.RegisterUserResponse;
import com.stevecoder.managementApp.user.infrastructure.api.dto.AuthResponseDTO;
import com.stevecoder.managementApp.user.infrastructure.api.dto.LoginUserDTO;
import com.stevecoder.managementApp.user.infrastructure.api.dto.RegisterUserDTO;
import com.stevecoder.managementApp.user.infrastructure.api.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final Mediator mediator;
    private final UserMapper userMapper;

    @PostMapping("/register")
    @Override
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid RegisterUserDTO dto) {
        RegisterUserResponse response = mediator.dispatch(userMapper.mapToRegisterUserRequest(dto));
        AuthResponseDTO authResponse = new AuthResponseDTO(null, response.getUser().getUsername(), response.getUser().getRole().name());
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginUserDTO dto) {
        AuthenticateUserResponse response = mediator.dispatch(userMapper.mapToAuthenticateUserRequest(dto));
        AuthResponseDTO authResponse = new AuthResponseDTO(response.getToken(), response.getUsername(), response.getRole());
        return ResponseEntity.ok(authResponse);
    }
}
