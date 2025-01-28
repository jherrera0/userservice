package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class EmptyUserEmailException extends RuntimeException {
    public EmptyUserEmailException() {
        super(ConstException.USER_EMAIL_EMPTY);
    }
}
