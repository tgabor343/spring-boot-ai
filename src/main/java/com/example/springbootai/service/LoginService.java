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

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(String username, String password) {
        return userRepository.findByUsername(username)
            .filter(user -> passwordEncoder.matches(password, user.getPasswordHash()))
            .map(user -> LoginResponse.ok(user.getUsername()))
            .orElse(LoginResponse.failure("Invalid username or password"));
    }
}
