package com.jolyvert.deliveryapp.service;


import com.jolyvert.deliveryapp.dto.RegisterCustomerDto;
import com.jolyvert.deliveryapp.dto.RegisterRestaurantDto;
import com.jolyvert.deliveryapp.model.Item;
import com.jolyvert.deliveryapp.model.Restaurant;
import com.jolyvert.deliveryapp.repository.ItemRepository;
import com.jolyvert.deliveryapp.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ItemRepository itemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, ItemRepository itemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.itemRepository = itemRepository;
    }

    public Restaurant createRestaurant(RegisterRestaurantDto registerRestaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(registerRestaurantDto.getRestaurantName());
        restaurant.setTelephoneNumber(registerRestaurantDto.getTelephoneNumber());
        restaurant.setAddress(registerRestaurantDto.getAddress());
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public String deleteRestaurant(Long restaurantId) {
        restaurantRepository.delete(restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("User Not Found")));
        return "Restaurant deleted";
    }

    public List<Item> addItem(int restaurantId, Item item) {
        Restaurant restaurant = restaurantRepository.findById((long)restaurantId).orElseThrow(() -> new RuntimeException("Restaurant Not Found"));
        restaurant.getItemList().add(item);
        restaurantRepository.save(restaurant);
        return restaurant.getItemList();
    }




}
