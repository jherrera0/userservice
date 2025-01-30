package com.backendchallenge.userservice.application.http.handler.interfaces;

public interface IAuthHandler {
    String login(String email, String password);
}