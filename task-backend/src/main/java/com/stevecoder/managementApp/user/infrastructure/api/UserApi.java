package com.stevecoder.managementApp.user.infrastructure.api;

import com.stevecoder.managementApp.user.infrastructure.api.dto.AuthResponseDTO;
import com.stevecoder.managementApp.user.infrastructure.api.dto.LoginUserDTO;
import com.stevecoder.managementApp.user.infrastructure.api.dto.RegisterUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication", description = "Endpoints para registro y autenticación de usuarios")
public interface UserApi {

    @Operation(summary = "Registrar usuario", description = "Crea una nueva cuenta de usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario registrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "Usuario ya existe")
    })
    ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid RegisterUserDTO dto);

    @Operation(summary = "Iniciar sesión", description = "Autentica un usuario y retorna un token JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login exitoso"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginUserDTO dto);
}
