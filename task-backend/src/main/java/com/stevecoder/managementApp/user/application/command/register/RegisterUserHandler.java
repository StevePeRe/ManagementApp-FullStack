package com.stevecoder.managementApp.user.application.command.register;

import com.stevecoder.managementApp.common.mediator.RequestHandler;
import com.stevecoder.managementApp.user.domain.entity.User;
import com.stevecoder.managementApp.user.domain.exception.UserAlreadyExistsException;
import com.stevecoder.managementApp.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterUserResponse handle(RegisterUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("username");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("email");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole() != null ? request.getRole() : com.stevecoder.managementApp.user.domain.enums.Role.USER)
                .build();

        User savedUser = userRepository.create(user);
        return new RegisterUserResponse(savedUser);
    }

    @Override
    public Class<RegisterUserRequest> getRequestType() {
        return RegisterUserRequest.class;
    }
}
