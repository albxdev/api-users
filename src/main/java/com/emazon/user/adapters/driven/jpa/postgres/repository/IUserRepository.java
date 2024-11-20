package com.emazon.user.adapters.driven.jpa.postgres.repository;

import com.emazon.user.adapters.driven.jpa.postgres.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing UserEntity objects.
 */
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email the email of the user to find
     * @return an Optional containing the found UserEntity, or empty if not found
     */
    Optional<UserEntity> findByEmail(String email);
}