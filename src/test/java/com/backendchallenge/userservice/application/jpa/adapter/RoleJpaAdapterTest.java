package com.backendchallenge.userservice.application.jpa.adapter;

import com.backendchallenge.userservice.application.jpa.entity.RoleEntity;
import com.backendchallenge.userservice.application.jpa.mapper.IRoleEntityMapper;
import com.backendchallenge.userservice.application.jpa.repository.IRoleRepository;
import com.backendchallenge.userservice.domain.model.Role;
import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleJpaAdapterTest {

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private IRoleEntityMapper roleEntityMapper;

    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;

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
    void getRoleByName_withExistingRole_shouldReturnRole() {
        String roleName = ConstTest.ROLE_NAME_TEST;
        Role role = new Role();
        role.setName(roleName);

        when(roleRepository.findByName(roleName)).thenReturn(new RoleEntity());
        when(roleEntityMapper.toDomain(any(RoleEntity.class))).thenReturn(role);

        Role result = roleJpaAdapter.getRoleByName(roleName);

        assertNotNull(result);
        assertEquals(roleName, result.getName());
    }

}