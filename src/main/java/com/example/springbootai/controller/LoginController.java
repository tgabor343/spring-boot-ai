package com.example.springbootai.controller;

import com.example.springbootai.config.JwtProperties;
import com.example.springbootai.dto.LoginRequest;
import com.example.springbootai.dto.LoginResponse;
import com.example.springbootai.service.JwtService;
import com.example.springbootai.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;

    public LoginController(LoginService loginService, AuthenticationManager authenticationManager,
                           JwtService jwtService, JwtProperties jwtProperties) {
        this.loginService = loginService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.jwtProperties = jwtProperties;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request,
                                                HttpServletResponse response) {
        LoginResponse loginResponse = loginService.login(request.username(), request.password());
        if (!loginResponse.success()) {
            return ResponseEntity.status(401).body(loginResponse);
        }
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        String token = jwtService.generateToken(request.username());
        Cookie cookie = new Cookie(jwtProperties.cookieName(), token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge((int) (jwtProperties.expirationMs() / 1000));
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(jwtProperties.cookieName(), "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok(Map.of("message", "Logout successful"));
    }
}
