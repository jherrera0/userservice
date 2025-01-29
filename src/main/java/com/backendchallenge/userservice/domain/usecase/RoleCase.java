package com.backendchallenge.userservice.domain.usecase;

import com.backendchallenge.userservice.domain.api.IRoleServicePort;
import com.backendchallenge.userservice.domain.exception.RoleEmptyException;
import com.backendchallenge.userservice.domain.model.Role;
import com.backendchallenge.userservice.domain.spi.IRolePersistencePort;

public class RoleCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Role getRoleByName(String name) {
        if (name == null || name.isBlank()) {
            throw new RoleEmptyException();
        }
        return rolePersistencePort.getRoleByName(name);
    }
}
