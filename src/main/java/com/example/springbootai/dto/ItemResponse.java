package com.example.springbootai.dto;

import com.example.springbootai.entity.Item;

import java.time.Instant;

public record ItemResponse(
    Long id,
    String name,
    String description,
    Instant createdAt
) {
    public static ItemResponse from(Item item) {
        return new ItemResponse(
            item.getId(),
            item.getName(),
            item.getDescription(),
            item.getCreatedAt()
        );
    }
}
