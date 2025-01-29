package com.backendchallenge.userservice.application.http.handler.interfaces;

import com.backendchallenge.userservice.application.http.dto.CreateOwnerRequest;

public interface IUserHandler {
    void createOwner(CreateOwnerRequest request);
    Boolean findOwnerById(Long ownerId);
}
