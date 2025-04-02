package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.exception.OrderInfoException;
import com.jolyvert.deliveryapp.exception.RestaurantException;
import com.jolyvert.deliveryapp.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    public List<Restaurant> getAllRestaurants();
    public String deleteRestaurant(Long restaurantId) throws RestaurantException;
    public String acceptOrder(Long orderId) throws OrderInfoException;
}
