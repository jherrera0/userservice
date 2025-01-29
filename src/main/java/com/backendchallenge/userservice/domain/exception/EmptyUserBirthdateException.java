package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class EmptyUserBirthdateException extends RuntimeException {
    public EmptyUserBirthdateException() {
        super(ConstExceptions.USER_BIRTHDATE_EMPTY);
    }
}
