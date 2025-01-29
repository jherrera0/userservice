package com.backendchallenge.userservice.application.http.handler;

import com.backendchallenge.userservice.application.http.dto.CreateOwnerRequest;
import com.backendchallenge.userservice.application.http.handler.interfaces.IUserHandler;
import com.backendchallenge.userservice.application.http.mapper.ICreateOwnerRequestMapper;
import com.backendchallenge.userservice.domain.api.IUserServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserHandler implements IUserHandler {
    private final ICreateOwnerRequestMapper createOwnerRequestMapper;
    public final IUserServicePort userServicePort;

    @Override
    public void createOwner(CreateOwnerRequest request) {
        userServicePort.createOwner(createOwnerRequestMapper.toUser(request));
    }

    @Override
    public Boolean findOwnerById(Long ownerId) {
        return userServicePort.findOwnerById(ownerId);
    }
}
