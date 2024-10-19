package com.emazon.user.adapters.driving.http.adapter;

import com.emazon.user.adapters.driving.http.dto.request.CreateUserRequestDTO;
import com.emazon.user.adapters.driving.http.dto.request.UpdateUserRequestDTO;
import com.emazon.user.adapters.driving.http.dto.response.CreateUserResponseDTO;
import com.emazon.user.adapters.driving.http.dto.response.UpdateUserResponseDTO;
import com.emazon.user.adapters.driving.http.mapper.IUserRequestMapper;
import com.emazon.user.adapters.driving.http.mapper.IUserResponseMapper;
import com.emazon.user.domain.api.IUserServicePort;
import com.emazon.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserHttpAdapter {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        User user = userRequestMapper.createRequestToUser(requestDTO);
        userServicePort.createUser(user);
        return userResponseMapper.toCreateUserResponseDTO(user);
    }

    public UpdateUserResponseDTO getUser(Long id) {
        User user = userServicePort.getUserById(id);
        return userResponseMapper.toUpdateUserResponseDTO(user);
    }

    public List<CreateUserResponseDTO> listUsers(Integer page, Integer size) {
        List<User> users = userServicePort.listUsers(page, size);
        return userResponseMapper.toCreateUserResponseDTOList(users);
    }

    public UpdateUserResponseDTO updateUser(Long id, UpdateUserRequestDTO request) {
        User existingUser = userServicePort.getUserById(id);
        User updatedUser = userRequestMapper.updateRequestToUser(request);
        updatedUser.setId(existingUser.getId());
        userServicePort.updateUserById(id, updatedUser);
        return userResponseMapper.toUpdateUserResponseDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        userServicePort.deleteUserById(id);
    }
}