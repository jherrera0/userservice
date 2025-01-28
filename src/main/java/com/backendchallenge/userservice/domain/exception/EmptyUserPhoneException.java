package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class EmptyUserPhoneException extends RuntimeException {
    public EmptyUserPhoneException() {
        super(ConstException.USER_PHONE_EMPTY);
    }
}
