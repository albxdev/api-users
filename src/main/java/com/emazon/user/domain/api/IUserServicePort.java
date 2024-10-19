package com.emazon.user.domain.api;

import com.emazon.user.domain.model.User;

import java.util.List;

public interface IUserServicePort {
    void createUser(User user);
    User getUserById(Long id);
    List<User> listUsers(Integer page, Integer size);
    void deleteUserById(Long id);
    void updateUserById(Long id, User user);
}
