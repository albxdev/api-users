package com.emazon.user.adapters.driven.jpa.postgres.adapter;

import com.emazon.user.adapters.driven.jpa.postgres.entity.UserEntity;
import com.emazon.user.adapters.driven.jpa.postgres.mapper.IUserEntityMapper;
import com.emazon.user.adapters.driven.jpa.postgres.repository.IUserRepository;
import com.emazon.user.domain.model.User;
import com.emazon.user.adapters.driven.jpa.postgres.exception.UserAlreadyExistsException;
import com.emazon.user.adapters.driven.jpa.postgres.exception.UserNotFoundException;
import com.emazon.user.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userEntityMapper.toEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public List<User> listUsers(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(userEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserById(Long id, User user) {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setIdentityDocument(user.getIdentityDocument());
        existingUser.setPhone(user.getPhone());
        existingUser.setBirthDate(user.getBirthDate());
        existingUser.setEmail(user.getEmail());

        userRepository.save(existingUser);
    }
}