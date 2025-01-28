package com.backendchallenge.userservice.application.jpa.repository;

import com.backendchallenge.userservice.application.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {
}
