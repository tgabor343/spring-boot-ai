package com.example.springbootai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(
    String cookieName,
    String secret,
    long expirationMs
) {}
