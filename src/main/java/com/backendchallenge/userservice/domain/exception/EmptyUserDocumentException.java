package com.backendchallenge.userservice.domain.exception;

import com.backendchallenge.userservice.domain.until.ConstException;

public class EmptyUserDocumentException extends RuntimeException {
    public EmptyUserDocumentException() {
        super(ConstException.USER_DOCUMENT_EMPTY);
    }
}
