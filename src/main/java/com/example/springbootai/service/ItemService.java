package com.example.springbootai.service;

import com.example.springbootai.dto.ItemResponse;
import com.example.springbootai.entity.Item;
import com.example.springbootai.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemResponse> listAll() {
        return itemRepository.findAll().stream()
            .map(ItemResponse::from)
            .toList();
    }

    public ItemResponse getDetails(Long id) {
        return itemRepository.findById(id)
            .map(ItemResponse::from)
            .orElse(null);
    }
}
