package com.backendchallenge.userservice.domain.exception.userexceptions;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class EmptyUserEmailException extends RuntimeException {
    public EmptyUserEmailException() {
        super(ConstExceptions.USER_EMAIL_EMPTY);
    }
}
