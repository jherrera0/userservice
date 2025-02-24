package com.backendchallenge.userservice.domain.exception.userexceptions;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class EmptyUserPhoneException extends RuntimeException {
    public EmptyUserPhoneException() {
        super(ConstExceptions.USER_PHONE_EMPTY);
    }
}
