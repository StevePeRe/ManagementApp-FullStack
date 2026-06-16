package com.stevecoder.managementApp.user.domain.port;

import com.stevecoder.managementApp.user.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    User create(User user);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
