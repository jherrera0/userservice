package com.backendchallenge.userservice.domain.exception.authexceptions;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class BadCredentialException extends RuntimeException {
    public BadCredentialException() {
        super(ConstExceptions.BAD_CREDENTIAL);
    }
}
