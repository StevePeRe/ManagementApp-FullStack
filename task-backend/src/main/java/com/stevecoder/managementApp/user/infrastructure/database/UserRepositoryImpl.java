package com.stevecoder.managementApp.user.infrastructure.database;

import com.stevecoder.managementApp.user.domain.entity.User;
import com.stevecoder.managementApp.user.domain.port.UserRepository;
import com.stevecoder.managementApp.user.infrastructure.database.entity.UserEntity;
import com.stevecoder.managementApp.user.infrastructure.database.mapper.UserEntityMapper;
import com.stevecoder.managementApp.user.infrastructure.database.repository.QueryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final QueryUserRepository repository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User create(User user) {
        UserEntity userEntity = userEntityMapper.mapToUserEntity(user);
        UserEntity saved = repository.save(userEntity);
        return userEntityMapper.mapToUser(saved);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username).map(userEntityMapper::mapToUser);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
