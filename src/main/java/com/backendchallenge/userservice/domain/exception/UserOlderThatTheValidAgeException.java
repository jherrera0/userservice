package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class UserOlderThatTheValidAgeException extends RuntimeException {
    public UserOlderThatTheValidAgeException() {
        super(ConstException.USER_OLDER_THAT_18_YEARS);
    }
}
