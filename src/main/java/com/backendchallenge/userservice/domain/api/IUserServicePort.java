package com.backendchallenge.userservice.domain.api;

import com.backendchallenge.userservice.domain.model.User;

public interface IUserServicePort {
    void createOwner(User user);
}
