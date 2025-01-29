package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class UserOlderThatTheValidAgeException extends RuntimeException {
    public UserOlderThatTheValidAgeException() {
        super(ConstExceptions.USER_OLDER_THAT_THE_VALID_AGE);
    }
}
