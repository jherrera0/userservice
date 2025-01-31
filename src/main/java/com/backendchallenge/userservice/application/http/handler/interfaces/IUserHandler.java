package com.backendchallenge.userservice.application.http.handler.interfaces;

import com.backendchallenge.userservice.application.http.dto.CreateUserRequest;
import jakarta.validation.Valid;

public interface IUserHandler {
    void createOwner(CreateUserRequest request);
    void createEmployee(CreateUserRequest request);
    Boolean findOwnerById(Long ownerId);
    void createClient(@Valid CreateUserRequest createUserRequest);
}
