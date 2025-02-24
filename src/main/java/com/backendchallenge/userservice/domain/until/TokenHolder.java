package com.backendchallenge.userservice.domain.until;

public class TokenHolder {
    private static final ThreadLocal<String> currentToken = new ThreadLocal<>();

    private TokenHolder() {
    }

    public static void setToken(String token) {
        currentToken.set(token);
    }

    public static String getToken() {
        return currentToken.get();
    }

    public static void clear() {
        currentToken.remove();
    }
}