package com.emazon.user.adapters.driven.jpa.postgres.mapper;

import com.emazon.user.adapters.driven.jpa.postgres.entity.UserEntity;
import com.emazon.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {

    // Mapeo automático sin necesidad de métodos personalizados
    @Mapping(target = "role.name", source = "role")
    UserEntity toEntity(User user);

    @Mapping(target = "role", source = "role.name")
    User toDomain(UserEntity userEntity);
}