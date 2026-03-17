package com.example.springbootai.dto;

public record LoginResponse(
    boolean success,
    String message,
    String username
) {
    public static LoginResponse ok(String username) {
        return new LoginResponse(true, "Login successful", username);
    }

    public static LoginResponse failure(String message) {
        return new LoginResponse(false, message, null);
    }
}
