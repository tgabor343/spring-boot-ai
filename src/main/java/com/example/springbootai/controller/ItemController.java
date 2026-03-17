package com.example.springbootai.controller;

import com.example.springbootai.dto.ItemResponse;
import com.example.springbootai.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@Tag(name = "Items", description = "API for managing items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    @Operation(summary = "List all items", description = "Retrieves a list of all items")
    public ResponseEntity<List<ItemResponse>> list() {
        List<ItemResponse> items = itemService.listAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get item details", description = "Retrieves the details of a specific item by its ID")
    public ResponseEntity<ItemResponse> details(@PathVariable Long id) {
        ItemResponse item = itemService.getDetails(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }
}
