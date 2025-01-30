package com.backendchallenge.userservice.infrastructure.controller;

import com.backendchallenge.userservice.application.http.dto.AuthRequest;
import com.backendchallenge.userservice.application.http.dto.AuthResponse;
import com.backendchallenge.userservice.application.http.handler.interfaces.IAuthHandler;
import com.backendchallenge.userservice.domain.until.ConstDocumentation;
import com.backendchallenge.userservice.domain.until.ConstRute;
import com.backendchallenge.userservice.domain.until.JwtConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(ConstRute.AUTH)
@Tag(name = ConstDocumentation.AUTH_TAG_NAME, description = ConstDocumentation.AUTH_TAG_DESCRIPTION)
public class AuthRestController {
    private final IAuthHandler authHandler;

    @Operation(summary = ConstDocumentation.AUTH_LOGIN_SUMMARY, description = ConstDocumentation.AUTH_LOGIN_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201,
                    description = ConstDocumentation.AUTH_LOGIN_DESCRIPTION_201, content = @Content),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403,
                    description = ConstDocumentation.AUTH_LOGIN_DESCRIPTION_403, content = @Content)
    })
    @PreAuthorize(JwtConst.PERMIT_ALL)
    @PostMapping(ConstRute.LOGIN)
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(authHandler.login(authRequest.getUsername(), authRequest.getPassword()));
    }

}
