package com.jolyvert.deliveryapp.controller;

import com.jolyvert.deliveryapp.dto.AddItemDto;
import com.jolyvert.deliveryapp.exception.FoodCartException;
import com.jolyvert.deliveryapp.model.Customer;
import com.jolyvert.deliveryapp.model.FoodCart;
import com.jolyvert.deliveryapp.model.Item;
import com.jolyvert.deliveryapp.service.FoodCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class FoodCartController {

    private final FoodCartService foodCartService;

    public FoodCartController(final FoodCartService foodCartService) {
        this.foodCartService = foodCartService;
    }

    @PostMapping("/addItemToCart")
    public ResponseEntity<FoodCart> addItemToCart(@RequestBody AddItemDto addItemDto) throws FoodCartException {
        return ResponseEntity.ok(foodCartService.addItem(addItemDto.getId(), addItemDto.getItem().getItemId()));
    }
}
