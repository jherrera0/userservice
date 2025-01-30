package com.backendchallenge.userservice.application.jpa.adapter;

import com.backendchallenge.userservice.application.jpa.entity.RoleEntity;
import com.backendchallenge.userservice.application.jpa.entity.UserEntity;
import com.backendchallenge.userservice.application.jpa.repository.IUserRepository;
import com.backendchallenge.userservice.application.jwt.JwtService;
import com.backendchallenge.userservice.domain.model.User;
import com.backendchallenge.userservice.domain.until.ConstExceptions;
import com.backendchallenge.userservice.domain.until.ConstTest;
import com.backendchallenge.userservice.domain.until.JwtConst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthJpaAdapterTest {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private IUserRepository userRepository;
    private AuthJpaAdapter authJpaAdapter;

    @BeforeEach
    void setUp() {
        authenticationManager = Mockito.mock(AuthenticationManager.class);
        jwtService = Mockito.mock(JwtService.class);
        userRepository = Mockito.mock(IUserRepository.class);
        authJpaAdapter = new AuthJpaAdapter(authenticationManager, jwtService, userRepository);
    }

    @Test
    void authenticate_withValidCredentials_returnsUser() {
        String email = ConstTest.EMAIL_VALID;
        String password = ConstTest.PASSWORD_VALID;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(ConstTest.ID_TEST);
        userEntity.setEmail(email);
        userEntity.setRole(new RoleEntity(ConstTest.ID_TEST, ConstTest.ROLE_NAME_TEST, new ArrayList<>()));

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userEntity);

        User result = authJpaAdapter.authenticate(email, password);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(ConstTest.ROLE_NAME_TEST, result.getRole());
    }

    @Test
    void authenticate_withInvalidCredentials_throwsBadCredentialsException() {
        String email = ConstTest.INVALID_EMAIL_TEST;
        String password = ConstTest.PASSWORD_VALID;

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException(ConstExceptions.BAD_CREDENTIAL));

        assertThrows(BadCredentialsException.class, () -> authJpaAdapter.authenticate(email, password));
    }

    @Test
    void generateToken_withValidUser_returnsToken() {
        User user = new User();
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setId(ConstTest.ID_TEST);
        user.setRole(ConstTest.ROLE_NAME_TEST);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtConst.ID, ConstTest.ID_TEST);
        extraClaims.put(JwtConst.ROLE, ConstTest.ROLE_NAME_TEST);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(ConstTest.ID_TEST);
        userEntity.setEmail(ConstTest.EMAIL_VALID);
        userEntity.setRole(new RoleEntity(ConstTest.ID_TEST, ConstTest.ROLE_NAME_TEST, new ArrayList<>()));

        when(jwtService.generateToken(user.getEmail(), extraClaims)).thenReturn(ConstTest.VALID_TOKEN);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(userEntity);

        String token = authJpaAdapter.generateToken(user);

        assertEquals(ConstTest.VALID_TOKEN, token);
    }

    @Test
    void generateToken_withNullUser_returnsNull() {
        String token = authJpaAdapter.generateToken(null);

        assertNull(token);
    }

    @Test
    void validateCredentials_withValidCredentials_returnsTrue() {
        String email = ConstTest.EMAIL_VALID;
        String password = ConstTest.PASSWORD_VALID;

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));

        boolean result = authJpaAdapter.validateCredentials(email, password);

        assertTrue(result);
    }

    @Test
    void validateCredentials_withInvalidCredentials_returnsFalse() {
        String email = ConstTest.INVALID_EMAIL_TEST;
        String password = ConstTest.PASSWORD_VALID;

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException(ConstExceptions.BAD_CREDENTIAL));

        boolean result = authJpaAdapter.validateCredentials(email, password);

        assertFalse(result);
    }
}