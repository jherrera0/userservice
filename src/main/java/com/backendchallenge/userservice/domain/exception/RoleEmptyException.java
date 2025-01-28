package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class RoleEmptyException extends RuntimeException {
    public RoleEmptyException() {
        super(ConstException.ROLE_EMPTY);
    }
}
