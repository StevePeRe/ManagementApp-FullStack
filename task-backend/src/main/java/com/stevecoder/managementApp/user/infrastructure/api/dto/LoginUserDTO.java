package com.stevecoder.managementApp.user.infrastructure.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Credenciales para login")
public class LoginUserDTO {
    @NotBlank
    @Schema(example = "johndoe")
    private String username;

    @NotBlank
    @Schema(example = "securepass123")
    private String password;
}
