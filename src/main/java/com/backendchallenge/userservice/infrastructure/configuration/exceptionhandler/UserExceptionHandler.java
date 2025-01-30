package com.backendchallenge.userservice.infrastructure.configuration.exceptionhandler;

import com.backendchallenge.userservice.domain.exception.userexceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmptyUserNameException.class)
    public ResponseEntity<Object> handleEmptyUserNameException(EmptyUserNameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmptyUserLastNameException.class)
    public ResponseEntity<Object> handleEmptyUserLastNameException(EmptyUserLastNameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmptyUserDocumentException.class)
    public ResponseEntity<Object> handleEmptyUserDocumentException(EmptyUserDocumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmptyUserPhoneException.class)
    public ResponseEntity<Object> handleEmptyUSerPhoneException(EmptyUserPhoneException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmptyUserBirthdateException.class)
    public ResponseEntity<Object> handleEmptyUserBirthdateException(EmptyUserBirthdateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmptyUserPasswordException.class)
    public ResponseEntity<Object> handleEmptyUserPasswordException(EmptyUserPasswordException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmptyUserEmailException.class)
    public ResponseEntity<Object> handleEmptyUserEmailException(EmptyUserEmailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserOlderThatTheValidAgeException.class)
    public ResponseEntity<Object> handleUserOlderThatTheValidAgeException(UserOlderThatTheValidAgeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidUserDocumentFormatException.class)
    public ResponseEntity<Object> handleInvalidUserDocumentFormatException(InvalidUserDocumentFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidUserEmailFormatException.class)
    public ResponseEntity<Object> handleInvalidUserEmailFormatException(InvalidUserEmailFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidUserPhoneFormatException.class)
    public ResponseEntity<Object> handleInvalidUserPhoneFormatException(InvalidUserPhoneFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
