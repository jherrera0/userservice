package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class EmptyUSerPhoneException extends RuntimeException {
    public EmptyUSerPhoneException() {
        super(ConstException.USER_PHONE_EMPTY);
    }
}
