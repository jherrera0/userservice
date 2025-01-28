package com.backendchallenge.userservice.application.jpa.repository;

import com.backendchallenge.userservice.application.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
