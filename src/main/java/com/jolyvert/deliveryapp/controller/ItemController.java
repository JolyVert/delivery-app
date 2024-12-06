package com.jolyvert.deliveryapp.controller;

import com.jolyvert.deliveryapp.exception.ItemException;
import com.jolyvert.deliveryapp.exception.RestaurantException;
import com.jolyvert.deliveryapp.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {

    private final ItemService itemService;

    public ItemController(final ItemService itemService) {
        this.itemService = itemService;
    }

    @DeleteMapping("/deleteItem/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable int itemId) throws ItemException {
        return ResponseEntity.ok(itemService.deleteItem(itemId));
    }

}
