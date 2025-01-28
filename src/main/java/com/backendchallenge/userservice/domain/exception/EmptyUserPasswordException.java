package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class EmptyUserPasswordException extends RuntimeException {
    public EmptyUserPasswordException() {
        super(ConstException.USER_PASSWORD_EMPTY);
    }
}
