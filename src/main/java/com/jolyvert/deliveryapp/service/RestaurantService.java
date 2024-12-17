package com.jolyvert.deliveryapp.service;



import com.jolyvert.deliveryapp.exception.RestaurantException;
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

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public String deleteRestaurant(Long restaurantId) throws RestaurantException {

        restaurantRepository.delete(restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantException("Restaurant does not exist")));
        return "Restaurant deleted";
    }

    public List<Item> addItem(int restaurantId, Item item) throws RestaurantException {


        Restaurant restaurant = restaurantRepository.findById((long)restaurantId).orElseThrow(() -> new RestaurantException("Restaurant Not Found"));
        restaurant.getItemList().add(item);
        restaurantRepository.save(restaurant);
        return restaurant.getItemList();
    }






}
