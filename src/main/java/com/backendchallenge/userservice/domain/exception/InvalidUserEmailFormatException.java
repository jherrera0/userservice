package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class InvalidUserEmailFormatException extends RuntimeException {
    public InvalidUserEmailFormatException() {
        super(ConstException.USER_EMAIL_INVALID_FORMAT);
    }
}
