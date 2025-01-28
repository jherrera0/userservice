package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class InvalidUserPhoneFormatException extends RuntimeException {
    public InvalidUserPhoneFormatException() {
        super(ConstException.USER_PHONE_INVALID_FORMAT);
    }
}
