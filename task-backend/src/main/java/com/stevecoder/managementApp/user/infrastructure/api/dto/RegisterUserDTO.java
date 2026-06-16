package com.stevecoder.managementApp.user.infrastructure.api.dto;

import com.stevecoder.managementApp.user.domain.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Datos para registro de usuario")
public class RegisterUserDTO {
    @NotBlank
    @Size(min = 3, max = 50)
    @Schema(example = "johndoe")
    private String username;

    @NotBlank
    @Size(min = 6)
    @Schema(example = "securepass123")
    private String password;

    @NotBlank
    @Email
    @Schema(example = "john@example.com")
    private String email;

    @Schema(example = "USER", allowableValues = {"USER", "ADMIN"})
    private Role role;
}
