package com.backendchallenge.userservice.domain.model;

import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(
                ConstTest.EMAIL_VALID,
                ConstTest.PASSWORD_VALID,
                ConstTest.DOCUMENT_VALID,
                ConstTest.PHONE_VALID,
                ConstTest.BIRTHDATE_VALID,
                ConstTest.NAME_VALID,
                ConstTest.LAST_NAME_VALID
        );
    }

    @Test
    void testGettersAndSetters() {
        user.setId(ConstTest.ROLE_ID_TEST);
        user.setEmail(ConstTest.EMAIL_NEW);
        user.setPassword(ConstTest.PASSWORD_NEW);
        user.setDocument(ConstTest.DOCUMENT_NEW);
        user.setPhone(ConstTest.PHONE_NEW);
        user.setBirthdate(ConstTest.BIRTHDATE_NEW);
        user.setName(ConstTest.NAME_NEW);
        user.setLastName(ConstTest.LAST_NAME_NEW);
        assertEquals(ConstTest.ROLE_ID_TEST, user.getId());
        assertEquals(ConstTest.EMAIL_NEW, user.getEmail());
        assertEquals(ConstTest.PASSWORD_NEW, user.getPassword());
        assertEquals(ConstTest.DOCUMENT_NEW, user.getDocument());
        assertEquals(ConstTest.PHONE_NEW, user.getPhone());
        assertEquals(ConstTest.BIRTHDATE_NEW, user.getBirthdate());
        assertEquals(ConstTest.NAME_NEW, user.getName());
        assertEquals(ConstTest.LAST_NAME_NEW, user.getLastName());
    }

    @Test
    void userCreation_withDefaultConstructor_shouldCreateUser() {
        user = new User();
        assertNotNull(user);
    }

    @Test
    void userCreation_withDefaultConstructor_shouldHaveNullFields() {
        user = new User();
        assertNull(user.getId());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getDocument());
        assertNull(user.getPhone());
        assertNull(user.getBirthdate());
        assertNull(user.getName());
        assertNull(user.getLastName());
    }

}