package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class InvalidUserEmailException extends RuntimeException {
    public InvalidUserEmailException() {
        super(ConstException.USER_EMAIL_INVALID_FORMAT);
    }
}
