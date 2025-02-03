package com.backendchallenge.userservice.application.http.handler;

import com.backendchallenge.userservice.application.http.dto.CreateUserRequest;
import com.backendchallenge.userservice.application.http.mapper.ICreateUserRequestMapper;
import com.backendchallenge.userservice.domain.api.IUserServicePort;
import com.backendchallenge.userservice.domain.model.User;
import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserHandlerTest {

    @Mock
    private ICreateUserRequestMapper createOwnerRequestMapper;

    @Mock
    private IUserServicePort userServicePort;

    @InjectMocks
    private UserHandler userHandler;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void createOwner_ValidRequest_CallsServiceLayer() {
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail(ConstTest.EMAIL_VALID);
        request.setPassword(ConstTest.PASSWORD_VALID);
        request.setDocument(ConstTest.DOCUMENT_VALID);
        request.setPhone(ConstTest.PHONE_VALID);
        request.setBirthdate(LocalDate.of(2000, 1, 1));
        request.setName(ConstTest.NAME_VALID);
        request.setLastName(ConstTest.LAST_NAME_VALID);

        User mockedUser = new User();
        when(createOwnerRequestMapper.toUser(request)).thenReturn(mockedUser);

        userHandler.createOwner(request);

        verify(createOwnerRequestMapper, times(1)).toUser(request);
        verify(userServicePort, times(1)).createOwner(mockedUser);
    }
    @Test
    void findOwnerById_ExistingOwnerId_ReturnsTrue() {
        Long ownerId = ConstTest.ID_TEST;
        when(userServicePort.findOwnerById(ownerId)).thenReturn(true);

        Boolean result = userHandler.findOwnerById(ownerId);

        assertTrue(result);
        verify(userServicePort, times(1)).findOwnerById(ownerId);
    }

    @Test
    void findOwnerById_NonExistingOwnerId_ReturnsFalse() {
        Long ownerId = ConstTest.ID_TEST;
        when(userServicePort.findOwnerById(ownerId)).thenReturn(false);

        Boolean result = userHandler.findOwnerById(ownerId);

        assertFalse(result);
        verify(userServicePort, times(1)).findOwnerById(ownerId);
    }

    @Test
    void createEmployee_ValidRequest_CallsServiceLayer() {
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail(ConstTest.EMAIL_VALID);
        request.setPassword(ConstTest.PASSWORD_VALID);
        request.setDocument(ConstTest.DOCUMENT_VALID);
        request.setPhone(ConstTest.PHONE_VALID);
        request.setBirthdate(ConstTest.BIRTHDATE_VALID);
        request.setName(ConstTest.NAME_VALID);
        request.setLastName(ConstTest.LAST_NAME_VALID);

        User mockedUser = new User();
        when(createOwnerRequestMapper.toUser(request)).thenReturn(mockedUser);

        userHandler.createEmployee(request, ConstTest.ID_TEST,ConstTest.VALID_TOKEN);

        verify(createOwnerRequestMapper, times(1)).toUser(request);
        verify(userServicePort, times(1)).createEmployee(mockedUser, ConstTest.ID_TEST);
    }
}