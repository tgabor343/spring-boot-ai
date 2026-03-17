package com.example.springbootai.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response object for login operation")
public record LoginResponse(
    @Schema(description = "Indicates if login was successful")
    boolean success,
    @Schema(description = "Message describing the result")
    String message,
    @Schema(description = "Username of the logged-in user")
    String username
) {
    public static LoginResponse ok(String username) {
        return new LoginResponse(true, "Login successful", username);
    }

    public static LoginResponse failure(String message) {
        return new LoginResponse(false, message, null);
    }
}
