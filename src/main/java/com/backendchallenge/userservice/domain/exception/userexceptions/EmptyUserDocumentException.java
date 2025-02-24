package com.backendchallenge.userservice.domain.exception.userexceptions;

import com.backendchallenge.userservice.domain.until.ConstExceptions;

public class EmptyUserDocumentException extends RuntimeException {
    public EmptyUserDocumentException() {
        super(ConstExceptions.USER_DOCUMENT_EMPTY);
    }
}
