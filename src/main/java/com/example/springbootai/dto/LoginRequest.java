package com.example.springbootai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request object for user login")
public record LoginRequest(

    @Schema(description = "Username for authentication", example = "user123")
    @NotBlank(message = "Username is required")
    String username,

    @Schema(description = "Password for authentication", example = "password123")
    @NotBlank(message = "Password is required")
    String password
) {}
