package com.example.springbootai.service;

import com.example.springbootai.dto.LoginResponse;
import com.example.springbootai.entity.User;
import com.example.springbootai.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(String username, String password) {
        return userRepository.findByUsername(username)
            .filter(user -> passwordEncoder.matches(password, user.getPasswordHash()))
            .map(user -> {
                String token = jwtService.generateToken(user.getUsername());
                return LoginResponse.ok(user.getUsername(), token);
            })
            .orElse(LoginResponse.failure("Invalid username or password"));
    }
}
