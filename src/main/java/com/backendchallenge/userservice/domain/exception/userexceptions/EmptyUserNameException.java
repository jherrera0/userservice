package com.backendchallenge.userservice.domain.exception.userexceptions;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class EmptyUserNameException extends RuntimeException {
    public EmptyUserNameException() {
        super(ConstExceptions.USER_NAME_EMPTY);
    }
}
