package com.emazon.user.adapters.driven.jpa.postgres.repository;

import com.emazon.user.adapters.driven.jpa.postgres.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

}