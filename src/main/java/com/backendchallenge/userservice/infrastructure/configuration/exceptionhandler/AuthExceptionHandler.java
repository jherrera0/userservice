package com.backendchallenge.userservice.infrastructure.configuration.exceptionhandler;

import com.backendchallenge.userservice.domain.exception.authexceptions.BadCredentialException;
import com.backendchallenge.userservice.domain.exception.authexceptions.MalformJwtException;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadCredentialException.class)
    public String handleBadCredentialException(BadCredentialException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MalformJwtException.class)
    public String handleMalformJwtException(MalformJwtException ex) {
        return ex.getMessage();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->

                errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
