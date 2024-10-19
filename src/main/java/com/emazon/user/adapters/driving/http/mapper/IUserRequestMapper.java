package com.emazon.user.adapters.driving.http.mapper;

import com.emazon.user.adapters.driving.http.dto.request.CreateUserRequestDTO;
import com.emazon.user.adapters.driving.http.dto.request.UpdateUserRequestDTO;
import com.emazon.user.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {

    User createRequestToUser(CreateUserRequestDTO createUserRequestDTO);

    User updateRequestToUser(UpdateUserRequestDTO updateUserRequestDTO);
}