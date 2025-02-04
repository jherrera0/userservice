package com.backendchallenge.userservice.domain.exception.userexceptions;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(ConstExceptions.USER_NOT_FOUND);
    }
}
