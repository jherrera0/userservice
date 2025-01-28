package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class EmptyUserBirthdateException extends RuntimeException {
    public EmptyUserBirthdateException() {
        super(ConstException.USER_BIRTHDATE_EMPTY);
    }
}
