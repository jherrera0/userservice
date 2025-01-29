package com.backendchallenge.userservice.application.jpa.adapter;

import com.backendchallenge.userservice.application.jpa.entity.RoleEntity;
import com.backendchallenge.userservice.application.jpa.entity.UserEntity;
import com.backendchallenge.userservice.application.jpa.mapper.IRoleEntityMapper;
import com.backendchallenge.userservice.application.jpa.mapper.IUserEntityMapper;
import com.backendchallenge.userservice.application.jpa.repository.IUserRepository;
import com.backendchallenge.userservice.domain.model.Role;
import com.backendchallenge.userservice.domain.model.User;
import com.backendchallenge.userservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUserWithRole(User user, Role role) {
        RoleEntity roleEntity = roleEntityMapper.toEntity(role);
        UserEntity userEntity = userEntityMapper.toEntity(user);
        userEntity.setRole(roleEntity);
        userRepository.save(userEntity);
    }
}
