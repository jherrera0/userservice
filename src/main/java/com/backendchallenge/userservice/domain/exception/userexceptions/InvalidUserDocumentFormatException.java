package com.backendchallenge.userservice.domain.exception.userexceptions;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class InvalidUserDocumentFormatException extends RuntimeException {
    public InvalidUserDocumentFormatException() {
        super(ConstExceptions.INVALID_USER_DOCUMENT_FORMAT);
    }
}
