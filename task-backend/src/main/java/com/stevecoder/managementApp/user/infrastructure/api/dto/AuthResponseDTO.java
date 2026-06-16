package com.stevecoder.managementApp.user.infrastructure.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Schema(description = "Respuesta de autenticación con token JWT")
public class AuthResponseDTO {
    @Schema(description = "Token JWT para autenticación", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "Nombre de usuario", example = "johndoe")
    private String username;

    @Schema(description = "Rol del usuario", example = "USER")
    private String role;
}
