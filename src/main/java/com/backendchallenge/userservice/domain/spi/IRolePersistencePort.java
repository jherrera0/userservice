package com.backendchallenge.userservice.domain.spi;

import com.backendchallenge.userservice.domain.model.Role;

public interface IRolePersistencePort {
    Role getRoleByName(String name);
}
