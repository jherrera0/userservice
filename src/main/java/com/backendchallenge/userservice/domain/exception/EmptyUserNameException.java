package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class EmptyUserNameException extends RuntimeException {
    public EmptyUserNameException() {
        super(ConstException.USER_NAME_EMPTY);
    }
}
