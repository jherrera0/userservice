package com.backendchallenge.userservice.domain.api;

import com.backendchallenge.userservice.domain.model.User;

public interface IUserServicePort {
    void createEmployee(User user);
    void createOwner(User user);
    Boolean findOwnerById(Long ownerId);
    void createClient(User user);
}
