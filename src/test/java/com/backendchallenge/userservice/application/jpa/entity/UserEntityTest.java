package com.backendchallenge.userservice.application.jpa.entity;

import com.backendchallenge.userservice.domain.until.ConstRole;
import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    private UserEntity userEntity;
    private RoleEntity roleEntity;

    @BeforeEach
    void setUp() {
        roleEntity = new RoleEntity(ConstTest.ID_TEST, ConstTest.ROLE_NAME_TEST, List.of());
        userEntity = new UserEntity(
                ConstTest.ID_TEST,
                ConstTest.EMAIL_VALID,
                ConstTest.PASSWORD_VALID,
                ConstTest.DOCUMENT_VALID,
                ConstTest.PHONE_VALID,
                ConstTest.BIRTHDATE_VALID,
                ConstTest.NAME_VALID,
                ConstTest.LAST_NAME_VALID,
                roleEntity);
    }

    @Test
    void getAuthorities_withValidRole_returnsGrantedAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userEntity.getAuthorities();

        assertNotNull(authorities);
        assertEquals(ConstTest.AUTHORITIES_SIZE, authorities.size());
        assertEquals(ConstRole.ROLE +ConstTest.ROLE_NAME_TEST, authorities.iterator().next().getAuthority());
    }


    @Test
    void getUsername_returnsEmail() {
        assertEquals(ConstTest.EMAIL_VALID, userEntity.getUsername());
    }

    @Test
    void isAccountNonExpired_returnsTrue() {
        assertTrue(userEntity.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked_returnsTrue() {
        assertTrue(userEntity.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired_returnsTrue() {
        assertTrue(userEntity.isCredentialsNonExpired());
    }

    @Test
    void isEnabled_returnsTrue() {
        assertTrue(userEntity.isEnabled());
    }
}