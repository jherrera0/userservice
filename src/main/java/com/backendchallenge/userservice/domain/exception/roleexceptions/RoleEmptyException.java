package com.backendchallenge.userservice.domain.exception.roleexceptions;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class RoleEmptyException extends RuntimeException {
    public RoleEmptyException() {
        super(ConstExceptions.ROLE_EMPTY);
    }
}
