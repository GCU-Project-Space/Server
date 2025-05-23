package com.example.recruitment.controller;

import com.example.recruitment.entity.Store;
import com.example.recruitment.repository.StoreRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreRepository storeRepository;

    @PostMapping
    public Store createStore(@Valid @RequestBody Store store) {
        return storeRepository.save(store);
    }

    @GetMapping
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }
}
