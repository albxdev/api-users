package com.emazon.user.domain.spi;

import com.emazon.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserPersistencePort {
    void createUser(User user);
    Optional<User> findByEmail(String email); // Changed from getUserByEmail to findByEmail
    Optional<User> getUserById(Long id);
    boolean existsById(Long id);
    List<User> listUsers(Integer page, Integer size);
    void deleteUserById(Long id);
    void updateUserById(Long id, User user);
}