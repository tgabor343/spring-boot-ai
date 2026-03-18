package com.example.springbootai.controller;

import com.example.springbootai.dto.LoginRequest;
import com.example.springbootai.dto.LoginResponse;
import com.example.springbootai.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Authentication", description = "API for user authentication")
public class LoginController {

    private final LoginService loginService;
    private final AuthenticationManager authenticationManager;

    public LoginController(LoginService loginService, AuthenticationManager authenticationManager) {
        this.loginService = loginService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "User login", description = "Authenticates the user and returns a JWT token")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request,
                                                HttpServletResponse response) {
        LoginResponse loginResponse = loginService.login(request.username(), request.password());
        if (!loginResponse.success()) {
            return ResponseEntity.status(401).body(loginResponse);
        }
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "User logout", description = "Invalidates the JWT token and logs out the user")
    public ResponseEntity<Map<String, String>> logout(HttpServletResponse response) {
        return ResponseEntity.ok(Map.of("message", "Logout successful"));
    }
}
