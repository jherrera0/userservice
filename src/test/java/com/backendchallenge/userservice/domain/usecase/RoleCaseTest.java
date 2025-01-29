package com.backendchallenge.userservice.domain.usecase;

import com.backendchallenge.userservice.domain.exception.RoleEmptyException;
import com.backendchallenge.userservice.domain.model.Role;
import com.backendchallenge.userservice.domain.spi.IRolePersistencePort;
import com.backendchallenge.userservice.domain.until.ConstException;
import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleCaseTest {

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleCase roleCase;

    private Role role;
    private AutoCloseable closeable;
    @BeforeEach
    void setUp() {
        role = new Role();
        role.setName(ConstTest.ROLE_NAME_TEST);
        role.setId(ConstTest.ROLE_ID_TEST);
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testGetRoleByName_ValidName() {
        // Arrange
        String roleName = ConstTest.ROLE_NAME_TEST;
        when(rolePersistencePort.getRoleByName(roleName)).thenReturn(role);

        // Act
        Role result = roleCase.getRoleByName(roleName);

        // Assert
        assertNotNull(result);
        assertEquals(roleName, result.getName());
        assertEquals(role.getId(), result.getId());
        verify(rolePersistencePort, times(1)).getRoleByName(roleName);
    }


    @Test
    void testGetRoleByName_EmptyName() {
        // Act & Assert
        assertThrows(RoleEmptyException.class, () -> roleCase.getRoleByName(ConstTest.EMPTY_STRING),
                ConstException.ROLE_EMPTY);
    }

    @Test
    void testGetRoleByName_NullName() {
        // Act & Assert
        assertThrows(RoleEmptyException.class, () -> roleCase.getRoleByName(null),
                ConstException.ROLE_EMPTY);
    }
}
