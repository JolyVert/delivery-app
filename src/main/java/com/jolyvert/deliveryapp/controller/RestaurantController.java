package com.jolyvert.deliveryapp.controller;

import com.jolyvert.deliveryapp.exception.RestaurantException;
import com.jolyvert.deliveryapp.model.Restaurant;
import com.jolyvert.deliveryapp.service.ItemService;
import com.jolyvert.deliveryapp.service.ItemServiceImpl;
import com.jolyvert.deliveryapp.service.RestaurantService;
import com.jolyvert.deliveryapp.service.RestaurantServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant")
@Controller
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final ItemService itemService;

    public RestaurantController(RestaurantService restaurantService, ItemService itemService) {
        this.restaurantService = restaurantService;
        this.itemService = itemService;
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




}
