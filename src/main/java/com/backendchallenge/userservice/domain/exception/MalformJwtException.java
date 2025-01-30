package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class MalformJwtException extends RuntimeException {
    public MalformJwtException() {
        super(ConstExceptions.MALFORMED_JWT);
    }
}
