package com.backendchallenge.userservice.infrastructure.configuration.exceptionhandler;

import com.backendchallenge.userservice.domain.exception.authexceptions.BadCredentialException;
import com.backendchallenge.userservice.domain.exception.authexceptions.MalformJwtException;
import com.backendchallenge.userservice.domain.until.ConstExceptions;
import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthExceptionHandlerTest {
    private AuthExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new AuthExceptionHandler();
    }

    @Test
    void handleBadCredentialException_returnsErrorMessage() {
        BadCredentialException exception = new BadCredentialException();
        String result = handler.handleBadCredentialException(exception);
        assertEquals(ConstExceptions.BAD_CREDENTIAL, result);
    }

    @Test
    void handleMalformJwtException_returnsErrorMessage() {
        MalformJwtException exception = new MalformJwtException();
        String result = handler.handleMalformJwtException(exception);
        assertEquals(ConstExceptions.MALFORMED_JWT, result);
    }
    @Test
    void handleMethodArgumentNotValid() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        HttpHeaders headers = new HttpHeaders();
        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        WebRequest request = mock(WebRequest.class);

        Map<String, Object> errors = new HashMap<>();
        errors.put(ConstTest.FIELD1, ConstTest.ERROR1);
        errors.put(ConstTest.FIELD2, ConstTest.ERROR2);

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("objectName", ConstTest.FIELD1, ConstTest.ERROR1),
                new FieldError("objectName", ConstTest.FIELD2, ConstTest.ERROR2)
        ));

        ResponseEntity<Object> response = handler.handleMethodArgumentNotValid(exception, headers, status, request);
        assert response != null;
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errors, response.getBody());
    }
}