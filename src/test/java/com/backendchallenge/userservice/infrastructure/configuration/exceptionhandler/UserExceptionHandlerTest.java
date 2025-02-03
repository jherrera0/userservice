package com.backendchallenge.userservice.infrastructure.configuration.exceptionhandler;

import com.backendchallenge.userservice.domain.exception.userexceptions.*;
import com.backendchallenge.userservice.domain.until.ConstExceptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserExceptionHandlerTest {

    @InjectMocks
    private UserExceptionHandler handler;

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
    void handleEmptyUserNameException_shouldReturnBadRequest() {
        EmptyUserNameException ex = new EmptyUserNameException();
        ResponseEntity<Object> response = handler.handleEmptyUserNameException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.USER_NAME_EMPTY, response.getBody());
    }

    @Test
    void handleEmptyUserLastNameException_shouldReturnBadRequest() {
        EmptyUserLastNameException ex = new EmptyUserLastNameException();
        ResponseEntity<Object> response = handler.handleEmptyUserLastNameException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.USER_LAST_NAME_EMPTY, response.getBody());
    }

    @Test
    void handleEmptyUserDocumentException_shouldReturnBadRequest() {
        EmptyUserDocumentException ex = new EmptyUserDocumentException();
        ResponseEntity<Object> response = handler.handleEmptyUserDocumentException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.USER_DOCUMENT_EMPTY, response.getBody());
    }

    @Test
    void handleEmptyUSerPhoneException_shouldReturnBadRequest() {
        EmptyUserPhoneException ex = new EmptyUserPhoneException();
        ResponseEntity<Object> response = handler.handleEmptyUSerPhoneException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.USER_PHONE_EMPTY, response.getBody());
    }
    @Test
    void handleEmptyUserBirthdateException_shouldReturnBadRequest() {
        EmptyUserBirthdateException ex = new EmptyUserBirthdateException();
        ResponseEntity<Object> response = handler.handleEmptyUserBirthdateException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.USER_BIRTHDATE_EMPTY, response.getBody());
    }

    @Test
    void handleEmptyUserPasswordException_shouldReturnBadRequest() {
        EmptyUserPasswordException ex = new EmptyUserPasswordException();
        ResponseEntity<Object> response = handler.handleEmptyUserPasswordException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.USER_PASSWORD_EMPTY, response.getBody());
    }

    @Test
    void handleEmptyUserEmailException_shouldReturnBadRequest() {
        EmptyUserEmailException ex = new EmptyUserEmailException();
        ResponseEntity<Object> response = handler.handleEmptyUserEmailException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.USER_EMAIL_EMPTY, response.getBody());
    }

    @Test
    void handleUserOlderThatTheValidAgeException_shouldReturnBadRequest() {
        UserOlderThatTheValidAgeException ex = new UserOlderThatTheValidAgeException();
        ResponseEntity<Object> response = handler.handleUserOlderThatTheValidAgeException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.USER_OLDER_THAT_THE_VALID_AGE, response.getBody());
    }

    @Test
    void handleInvalidUserDocumentFormatException_shouldReturnBadRequest() {
        InvalidUserDocumentFormatException ex = new InvalidUserDocumentFormatException();
        ResponseEntity<Object> response = handler.handleInvalidUserDocumentFormatException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.INVALID_USER_DOCUMENT_FORMAT, response.getBody());
    }

    @Test
    void handleInvalidUserEmailFormatException_shouldReturnBadRequest() {
        InvalidUserEmailFormatException ex = new InvalidUserEmailFormatException();
        ResponseEntity<Object> response = handler.handleInvalidUserEmailFormatException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.USER_EMAIL_INVALID_FORMAT, response.getBody());
    }

    @Test
    void handleInvalidUserPhoneFormatException_shouldReturnBadRequest() {
        InvalidUserPhoneFormatException ex = new InvalidUserPhoneFormatException();
        ResponseEntity<Object> response = handler.handleInvalidUserPhoneFormatException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.USER_PHONE_INVALID_FORMAT, response.getBody());
    }
    @Test
    void handleUserAlreadyExistsException_shouldReturnBadRequest() {
        UserAlreadyExistsException ex = new UserAlreadyExistsException();
        ResponseEntity<Object> response = handler.handleUserAlreadyExistsException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.USER_ALREADY_EXISTS, response.getBody());
    }
}