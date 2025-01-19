package com.jolyvert.deliveryapp.controller;

import com.jolyvert.deliveryapp.exception.ItemException;
import com.jolyvert.deliveryapp.exception.RestaurantException;
import com.jolyvert.deliveryapp.model.Item;
import com.jolyvert.deliveryapp.service.ItemService;
import com.jolyvert.deliveryapp.service.ItemServiceImpl;
import com.jolyvert.deliveryapp.service.RestaurantService;
import com.jolyvert.deliveryapp.service.RestaurantServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant/{restaurantName}")
@Controller
public class ItemController {

    private final ItemService itemService;

    public ItemController(final ItemService itemService, RestaurantService restaurantService) {
        this.itemService = itemService;
    }

    @PostMapping("/addItem")
    public ResponseEntity<List<Item>> addItem(@PathVariable String restaurantName, @RequestBody Item item) throws RestaurantException {
        return ResponseEntity.ok(itemService.addItem(restaurantName, item));
    }

    @DeleteMapping("/deleteItem/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable int itemId) throws ItemException {
        return ResponseEntity.ok(itemService.deleteItem(itemId));
    }

    @PutMapping("/updateItem/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable int itemId, @RequestBody Item item) throws ItemException {
        return ResponseEntity.ok(itemService.updateItem(itemId, item));
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItemList(@PathVariable String restaurantName) throws RestaurantException {
        return ResponseEntity.ok(itemService.getItemList(restaurantName));
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<Item> getItem(@PathVariable int itemId) throws ItemException {
        return ResponseEntity.ok(itemService.getItem(itemId));
    }



}
