package com.example.springbootai.dto;

import com.example.springbootai.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Response object for item details")
public record ItemResponse(
    @Schema(description = "Unique identifier of the item")
    Long id,
    @Schema(description = "Name of the item")
    String name,
    @Schema(description = "Description of the item")
    String description,
    @Schema(description = "Creation timestamp")
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
