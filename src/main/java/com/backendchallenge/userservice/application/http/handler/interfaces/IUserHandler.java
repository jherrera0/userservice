package com.backendchallenge.userservice.application.http.handler.interfaces;

import com.backendchallenge.userservice.application.http.dto.CreateUserRequest;

public interface IUserHandler {
    void createOwner(CreateUserRequest request);
    void createEmployee(CreateUserRequest request,Long restaurantId, String token);
    Boolean findOwnerById(Long ownerId);
}
