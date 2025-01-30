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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    })
    @PostMapping(ConstRute.CREATE_OWNER_RUTE)
    public ResponseEntity<String> createOwner(@Valid @RequestBody CreateOwnerRequest createOwnerRequest) {
        userHandler.createOwner(createOwnerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ConstDocumentation.FIND_OWNER_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.FIND_OWNER_CODE_201),
    })
    @GetMapping(ConstRute.FIND_OWNER_BY_ID_RUTE)
    public ResponseEntity<Boolean> findOwnerById(@RequestParam Long ownerId) {
        return ResponseEntity.ok(userHandler.findOwnerById(ownerId));
    }
}
