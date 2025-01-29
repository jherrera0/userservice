package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class EmptyUserLastNameException extends RuntimeException {
    public EmptyUserLastNameException() {
        super(ConstExceptions.USER_LAST_NAME_EMPTY);
    }
}
