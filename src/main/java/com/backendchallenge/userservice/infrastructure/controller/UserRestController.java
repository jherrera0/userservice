package com.backendchallenge.userservice.infrastructure.controller;

import com.backendchallenge.userservice.application.http.dto.CreateOwnerRequest;
import com.backendchallenge.userservice.application.http.handler.interfaces.IUserHandler;
import com.backendchallenge.userservice.domain.until.ConstDocumentation;
import com.backendchallenge.userservice.domain.until.ConstRute;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(ConstRute.USER_REST_RUTE)
@Tag(name = ConstDocumentation.USER_REST_NAME, description = ConstDocumentation.USER_REST_DESCRIPTION)
public class UserRestController {
    private final IUserHandler userHandler;
    @Operation(summary = ConstDocumentation.CREATE_OWNER_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.CREATE_OWNER_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.CREATE_OWNER_CODE_400),
    })
    public void createOwner(@RequestBody @Valid CreateOwnerRequest createOwnerRequest) {
        userHandler.createOwner(createOwnerRequest);
    }
}
