package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.exception.RestaurantException;
import com.jolyvert.deliveryapp.model.Item;
import com.jolyvert.deliveryapp.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    public List<Restaurant> getAllRestaurants();
    public String deleteRestaurant(Long restaurantId) throws RestaurantException;
    public List<Item> addItem(int restaurantId, Item item) throws RestaurantException;
}
