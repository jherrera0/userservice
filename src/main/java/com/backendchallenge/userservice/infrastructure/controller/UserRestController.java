package com.backendchallenge.userservice.infrastructure.controller;

import com.backendchallenge.userservice.application.http.dto.CreateUserRequest;
import com.backendchallenge.userservice.application.http.handler.interfaces.IUserHandler;
import com.backendchallenge.userservice.domain.until.ConstDocumentation;
import com.backendchallenge.userservice.domain.until.ConstRute;
import com.backendchallenge.userservice.domain.until.JwtConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
            @ApiResponse(responseCode = ConstDocumentation.CODE_403, description = ConstDocumentation.CREATE_OWNER_CODE_403),
    })
    @PreAuthorize(JwtConst.HAS_ROLE_ADMIN)
    @PostMapping(ConstRute.CREATE_OWNER_RUTE)
    public ResponseEntity<String> createOwner(@Valid @RequestBody CreateUserRequest createUserRequest) {
        userHandler.createOwner(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ConstDocumentation.FIND_OWNER_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.FIND_OWNER_CODE_201),
    })
    @PreAuthorize(JwtConst.PERMIT_ALL)
    @GetMapping(ConstRute.FIND_OWNER_BY_ID_RUTE)
    public ResponseEntity<Boolean> findOwnerById(@RequestParam Long ownerId) {
        return ResponseEntity.ok(userHandler.findOwnerById(ownerId));
    }

    @Operation(summary = ConstDocumentation.CREATE_EMPLOYEE_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.CREATE_EMPLOYEE_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.CREATE_EMPLOYEE_CODE_400),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403, description = ConstDocumentation.CREATE_EMPLOYEE_CODE_403),
    })
    @PreAuthorize(JwtConst.HAS_ROLE_OWNER)
    @PostMapping(ConstRute.CREATE_EMPLOYEE_RUTE)
    public ResponseEntity<String> createEmployee(@Valid @RequestBody CreateUserRequest createUserRequest,
                                                 @RequestParam Long restaurantId,
                                                 @RequestHeader(JwtConst.HEADER_STRING) String token) {
        userHandler.createEmployee(createUserRequest, restaurantId,token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ConstDocumentation.CREATE_CLIENT_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.CREATE_CLIENT_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.CREATE_CLIENT_CODE_400),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403, description = ConstDocumentation.CREATE_CLIENT_CODE_403),
    })
    @PreAuthorize(JwtConst.PERMIT_ALL)
    @PostMapping(ConstRute.CREATE_CLIENT_RUTE)
    public ResponseEntity<String> createClient(@Valid @RequestBody CreateUserRequest createUserRequest) {
        userHandler.createClient(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ConstDocumentation.GET_USER_PHONE_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.GET_USER_PHONE_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.GET_USER_PHONE_CODE_400),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403, description = ConstDocumentation.GET_USER_PHONE_CODE_403),
    })
    @PreAuthorize(JwtConst.HAS_ROLE_EMPLOYEE)
    @GetMapping(ConstRute.GET_USER_PHONE_RUTE)
    public ResponseEntity<String> getPhone(@RequestParam Long userId) {
        return ResponseEntity.ok(userHandler.getPhone(userId));
    }
}
