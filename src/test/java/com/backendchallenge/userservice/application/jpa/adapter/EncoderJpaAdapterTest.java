package com.backendchallenge.userservice.application.jpa.adapter;

import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EncoderJpaAdapterTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EncoderJpaAdapter encoderJpaAdapter;

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
    void encode_withValidPassword_shouldReturnEncodedPassword() {
        String plainPassword = ConstTest.PASSWORD_VALID;
        String encodedPassword = ConstTest.ENCODED_PASSWORD_TEST;

        when(passwordEncoder.encode(plainPassword)).thenReturn(encodedPassword);

        String result = encoderJpaAdapter.encode(plainPassword);

        assertEquals(encodedPassword, result);
        verify(passwordEncoder, times(1)).encode(plainPassword);
    }

}