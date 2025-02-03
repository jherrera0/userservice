package com.backendchallenge.userservice.application.jpa.repository;

import com.backendchallenge.userservice.application.jpa.entity.RoleEntity;
import com.backendchallenge.userservice.application.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UserEntity u WHERE u.id = :id AND u.role = :role")
    Boolean findByIdAndRole(Long id, RoleEntity role);

    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);
}
