package com.stevecoder.managementApp.user.domain.entity;

import com.stevecoder.managementApp.user.domain.enums.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;
}
