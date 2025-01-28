package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class EmptyUserLastNameException extends RuntimeException {
    public EmptyUserLastNameException() {
        super(ConstException.USER_LAST_NAME_EMPTY);
    }
}
