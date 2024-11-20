package com.emazon.user.adapters.driven.jpa.postgres.mapper;

import com.emazon.user.adapters.driven.jpa.postgres.entity.UserEntity;
import com.emazon.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}
