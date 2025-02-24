package com.backendchallenge.userservice.domain.api;

import com.backendchallenge.userservice.domain.model.Role;

public interface IRoleServicePort {
    Role getRoleByName(String name);
}
