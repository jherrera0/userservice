package com.backendchallenge.userservice.domain.api;

import com.backendchallenge.userservice.domain.model.User;

public interface IUserServicePort {
    void createEmployee(User user,Long restaurantId);
    void createOwner(User user);
    Boolean findOwnerById(Long ownerId);
    void createClient(User user);
    String getPhoneById(Long userId);
}
