package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class InvalidUserPhoneFormatException extends RuntimeException {
    public InvalidUserPhoneFormatException() {
        super(ConstExceptions.USER_PHONE_INVALID_FORMAT);
    }
}
