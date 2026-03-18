package com.example.springbootai.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response object for login operation")
public record LoginResponse(
    @Schema(description = "Indicates if login was successful")
    boolean success,
    @Schema(description = "Message describing the result")
    String message,
    @Schema(description = "Username of the logged-in user")
    String username,
    @Schema(description = "JWT token for authentication")
    String token
) {
    public static LoginResponse ok(String username, String token) {
        return new LoginResponse(true, "Login successful", username, token);
    }

    public static LoginResponse failure(String message) {
        return new LoginResponse(false, message, null, null);
    }
}
