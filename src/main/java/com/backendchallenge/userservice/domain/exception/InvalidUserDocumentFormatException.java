package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class InvalidUserDocumentFormatException extends RuntimeException {
    public InvalidUserDocumentFormatException() {
        super(ConstException.INVALID_USER_DOCUMENT_FORMAT);
    }
}
