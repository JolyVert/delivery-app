package com.jolyvert.deliveryapp.controller;

import com.jolyvert.deliveryapp.dto.AddItemDto;
import com.jolyvert.deliveryapp.exception.FoodCartException;
import com.jolyvert.deliveryapp.model.FoodCart;
import com.jolyvert.deliveryapp.service.FoodCartService;
import com.jolyvert.deliveryapp.service.FoodCartServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class FoodCartController {

    private final FoodCartService foodCartService;

    public FoodCartController(final FoodCartService foodCartService) {
        this.foodCartService = foodCartService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addItemToCart")
    public ResponseEntity<FoodCart> addItemToCart(@RequestBody AddItemDto addItemDto) throws FoodCartException {
        return ResponseEntity.ok(foodCartService.addItem(addItemDto.getId(), addItemDto.getItem().getItemId()));
    }
}
