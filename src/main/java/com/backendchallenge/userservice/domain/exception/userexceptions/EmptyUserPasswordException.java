package com.backendchallenge.userservice.domain.exception.userexceptions;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class EmptyUserPasswordException extends RuntimeException {
    public EmptyUserPasswordException() {
        super(ConstExceptions.USER_PASSWORD_EMPTY);
    }
}
