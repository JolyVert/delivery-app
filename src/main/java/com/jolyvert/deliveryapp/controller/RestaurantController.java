package com.jolyvert.deliveryapp.controller;

import com.jolyvert.deliveryapp.dto.AddItemDto;
import com.jolyvert.deliveryapp.dto.RegisterRestaurantDto;
import com.jolyvert.deliveryapp.exception.RestaurantException;
import com.jolyvert.deliveryapp.model.Item;
import com.jolyvert.deliveryapp.model.Restaurant;
import com.jolyvert.deliveryapp.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody RegisterRestaurantDto registerRestaurantDto) throws RestaurantException {
        Restaurant restaurant = restaurantService.createRestaurant(registerRestaurantDto);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @DeleteMapping("/deleteRestaurant/{restaurantId}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long restaurantId) throws RestaurantException {
        return ResponseEntity.ok(restaurantService.deleteRestaurant(restaurantId));
    }

    @PostMapping("/restaurant/addItem")
    public ResponseEntity<List<Item>> addItem(@RequestBody AddItemDto addItemDto) throws RestaurantException {
        return ResponseEntity.ok(restaurantService.addItem(addItemDto.getId(), addItemDto.getItem()));
    }


}
