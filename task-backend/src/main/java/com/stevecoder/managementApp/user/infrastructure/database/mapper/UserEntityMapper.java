package com.stevecoder.managementApp.user.infrastructure.database.mapper;

import com.stevecoder.managementApp.user.domain.entity.User;
import com.stevecoder.managementApp.user.infrastructure.database.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    UserEntity mapToUserEntity(User user);

    User mapToUser(UserEntity userEntity);
}
