package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class InvalidUserEmailFormatException extends RuntimeException {
    public InvalidUserEmailFormatException() {
        super(ConstExceptions.USER_EMAIL_INVALID_FORMAT);
    }
}
