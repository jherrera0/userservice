package com.backendchallenge.userservice.application.jpa.adapter;

import com.backendchallenge.userservice.application.jpa.entity.RoleEntity;
import com.backendchallenge.userservice.application.jpa.entity.UserEntity;
import com.backendchallenge.userservice.application.jpa.mapper.IRoleEntityMapper;
import com.backendchallenge.userservice.application.jpa.mapper.IUserEntityMapper;
import com.backendchallenge.userservice.application.jpa.repository.IUserRepository;
import com.backendchallenge.userservice.domain.model.Role;
import com.backendchallenge.userservice.domain.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UserJpaAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IRoleEntityMapper roleEntityMapper;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void saveUserWithRole_withValidUserAndRole_shouldSaveUser() {
        User user = new User();
        Role role = new Role();
        RoleEntity roleEntity = new RoleEntity();
        UserEntity userEntity = new UserEntity();

        when(roleEntityMapper.toEntity(role)).thenReturn(roleEntity);
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);

        userJpaAdapter.saveUserWithRole(user, role);

        verify(userRepository, times(1)).save(userEntity);
        verify(userEntityMapper, times(1)).toEntity(user);
        verify(roleEntityMapper, times(1)).toEntity(role);
    }

}