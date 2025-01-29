package com.backendchallenge.userservice.domain.model;

import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role(
                ConstTest.ROLE_ID_TEST,
                ConstTest.ROLE_NAME_TEST
        );
    }

    @Test
    void testGettersAndSetters() {
        role.setId(ConstTest.ROLE_ID_NEW);
        role.setName(ConstTest.ROLE_NAME_NEW);
        assertEquals(ConstTest.ROLE_ID_NEW, role.getId());
        assertEquals(ConstTest.ROLE_NAME_NEW, role.getName());
    }

    @Test
    void roleCreation_withDefaultConstructor_shouldCreateRole() {
        role = new Role();
        assertNotNull(role);
    }

    @Test
    void roleCreation_withDefaultConstructor_shouldHaveNullFields() {
        role = new Role();
        assertNull(role.getId());
        assertNull(role.getName());
    }

}