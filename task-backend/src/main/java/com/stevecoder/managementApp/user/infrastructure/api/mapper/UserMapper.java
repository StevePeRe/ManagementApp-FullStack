package com.stevecoder.managementApp.user.infrastructure.api.mapper;

import com.stevecoder.managementApp.user.application.command.authenticate.AuthenticateUserRequest;
import com.stevecoder.managementApp.user.application.command.register.RegisterUserRequest;
import com.stevecoder.managementApp.user.infrastructure.api.dto.LoginUserDTO;
import com.stevecoder.managementApp.user.infrastructure.api.dto.RegisterUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    RegisterUserRequest mapToRegisterUserRequest(RegisterUserDTO dto);

    AuthenticateUserRequest mapToAuthenticateUserRequest(LoginUserDTO dto);
}
