package com.emazon.user.adapters.driving.http.mapper;

import com.emazon.user.adapters.driving.http.dto.response.CreateUserResponseDTO;
import com.emazon.user.adapters.driving.http.dto.response.UpdateUserResponseDTO;
import com.emazon.user.domain.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserResponseMapper {

    CreateUserResponseDTO toCreateUserResponseDTO(User user);

    UpdateUserResponseDTO toUpdateUserResponseDTO(User user);

    List<CreateUserResponseDTO> toCreateUserResponseDTOList(List<User> userList);
}