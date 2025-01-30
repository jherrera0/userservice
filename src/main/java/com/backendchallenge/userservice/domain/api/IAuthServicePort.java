package com.backendchallenge.userservice.domain.api;

public interface IAuthServicePort {
    String login(String email, String password);
}
