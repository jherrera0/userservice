package com.backendchallenge.userservice.domain.spi;

public interface IEncoderPersistencePort {
    String encode(String password);
}
