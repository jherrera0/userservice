package com.backendchallenge.userservice.infrastructure.controller;

import com.backendchallenge.userservice.application.http.dto.CreateUserRequest;
import com.backendchallenge.userservice.application.http.handler.interfaces.IUserHandler;
import com.backendchallenge.userservice.domain.until.ConstRute;
import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRestControllerTest {

    @Mock
    private IUserHandler userHandler;

    @InjectMocks
    private UserRestController userRestController;

    private MockMvc mockMvc;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void createOwner_withValidRequest_shouldReturnStatus201() throws Exception {
        CreateUserRequest request = new CreateUserRequest(
                ConstTest.EMAIL_VALID,
                ConstTest.PASSWORD_VALID,
                ConstTest.DOCUMENT_VALID,
                ConstTest.PHONE_VALID,
                ConstTest.BIRTHDATE_VALID,
                ConstTest.NAME_VALID,
                ConstTest.LAST_NAME_VALID
        );

        mockMvc.perform(post(ConstRute.USER_REST_RUTE + ConstRute.CREATE_OWNER_RUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "email": "%s",
                    "password": "%s",
                    "document": "%s",
                    "phone": "%s",
                    "birthdate": "%s",
                    "name": "%s",
                    "lastName": "%s"
                }
            """.formatted(ConstTest.EMAIL_VALID, ConstTest.PASSWORD_VALID, ConstTest.DOCUMENT_VALID, ConstTest.PHONE_VALID, ConstTest.BIRTHDATE_STRING_VALID, ConstTest.NAME_VALID, ConstTest.LAST_NAME_VALID)))
                .andExpect(status().isCreated());

        verify(userHandler).createOwner(request);
    }

    @Test
    void createOwner_withInvalidRequest_shouldReturnStatus400() throws Exception {
        mockMvc.perform(post(ConstRute.USER_REST_RUTE + ConstRute.CREATE_OWNER_RUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "email": "",
                        "password": "",
                        "document": "",
                        "phone": "",
                        "birthdate": "01/01/2020",
                        "name": "",
                        "lastName": ""
                    }
                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findOwnerById_withExistingOwnerId_shouldReturnStatus200() throws Exception {
        Long ownerId = ConstTest.ID_TEST;

        when(userHandler.findOwnerById(ownerId)).thenReturn(true);

        mockMvc.perform(get(ConstRute.USER_REST_RUTE + ConstRute.FIND_OWNER_BY_ID_RUTE)
                        .param("ownerId", ownerId.toString()))
                .andExpect(status().isOk());

        verify(userHandler).findOwnerById(ownerId);
    }

    @Test
    void findOwnerById_withNonExistingOwnerId_shouldReturnStatus200WithFalseBody() throws Exception {
        Long ownerId = ConstTest.ID_TEST;

        when(userHandler.findOwnerById(ownerId)).thenReturn(false);

        mockMvc.perform(get(ConstRute.USER_REST_RUTE + ConstRute.FIND_OWNER_BY_ID_RUTE)
                        .param("ownerId", ownerId.toString()))
                .andExpect(status().isOk());

        verify(userHandler).findOwnerById(ownerId);
    }

    @Test
    void createEmployee_withValidRequest_shouldReturnStatus201() throws Exception {
        CreateUserRequest request = new CreateUserRequest(
                ConstTest.EMAIL_VALID,
                ConstTest.PASSWORD_VALID,
                ConstTest.DOCUMENT_VALID,
                ConstTest.PHONE_VALID,
                ConstTest.BIRTHDATE_VALID,
                ConstTest.NAME_VALID,
                ConstTest.LAST_NAME_VALID
        );

        mockMvc.perform(post(ConstRute.USER_REST_RUTE + ConstRute.CREATE_EMPLOYEE_RUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "email": "%s",
                    "password": "%s",
                    "document": "%s",
                    "phone": "%s",
                    "birthdate": "%s",
                    "name": "%s",
                    "lastName": "%s"
                }
            """.formatted(ConstTest.EMAIL_VALID, ConstTest.PASSWORD_VALID, ConstTest.DOCUMENT_VALID, ConstTest.PHONE_VALID, ConstTest.BIRTHDATE_STRING_VALID, ConstTest.NAME_VALID, ConstTest.LAST_NAME_VALID)))
                .andExpect(status().isCreated());

        verify(userHandler).createEmployee(request);
    }

    @Test
    void createEmployee_withInvalidRequest_shouldReturnStatus400() throws Exception {
        mockMvc.perform(post(ConstRute.USER_REST_RUTE + ConstRute.CREATE_EMPLOYEE_RUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "email": "",
                    "password": "",
                    "document": "",
                    "phone": "",
                    "birthdate": "01/01/2020",
                    "name": "",
                    "lastName": ""
                }
            """))
                .andExpect(status().isBadRequest());
    }
}