package com.backendchallenge.userservice.application.jpa.adapter;

import com.backendchallenge.userservice.domain.spi.IEncoderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class EncoderJpaAdapter implements IEncoderPersistencePort {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
