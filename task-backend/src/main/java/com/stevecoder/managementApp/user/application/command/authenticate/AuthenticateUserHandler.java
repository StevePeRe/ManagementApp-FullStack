package com.stevecoder.managementApp.user.application.command.authenticate;

import com.stevecoder.managementApp.common.mediator.RequestHandler;
import com.stevecoder.managementApp.common.security.JwtService;
import com.stevecoder.managementApp.user.domain.entity.User;
import com.stevecoder.managementApp.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUserHandler implements RequestHandler<AuthenticateUserRequest, AuthenticateUserResponse> {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public AuthenticateUserResponse handle(AuthenticateUserRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
        return new AuthenticateUserResponse(token, user.getUsername(), user.getRole().name());
    }

    @Override
    public Class<AuthenticateUserRequest> getRequestType() {
        return AuthenticateUserRequest.class;
    }
}
