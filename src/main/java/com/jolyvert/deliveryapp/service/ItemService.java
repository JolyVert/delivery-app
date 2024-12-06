package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.exception.ItemException;
import com.jolyvert.deliveryapp.exception.RestaurantException;
import com.jolyvert.deliveryapp.model.FoodCart;
import com.jolyvert.deliveryapp.model.Item;
import com.jolyvert.deliveryapp.model.Restaurant;
import com.jolyvert.deliveryapp.repository.FoodCartRepository;
import com.jolyvert.deliveryapp.repository.ItemRepository;
import com.jolyvert.deliveryapp.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodCartRepository foodCartRepository;

    public ItemService(ItemRepository itemRepository, RestaurantRepository restaurantRepository, FoodCartRepository foodCartRepository) {
        this.itemRepository = itemRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodCartRepository = foodCartRepository;
    }

    public List<Item> addItem(int restaurantId, Item item) throws RestaurantException {



        Restaurant restaurant = restaurantRepository.findById((long)restaurantId).orElseThrow(() -> new RestaurantException("Restaurant Not Found"));
        restaurant.getItemList().add(item);
        restaurantRepository.save(restaurant);
        return restaurant.getItemList();
    }

    public String deleteItem(int itemId) throws ItemException {
        List<FoodCart> foodCartList = foodCartRepository.findAll();
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        Item item = itemRepository.findById((long)itemId).orElseThrow(() -> new ItemException("Item Not Found"));
        for (FoodCart foodCart : foodCartList) {
            if (foodCart.getItemList().contains(item)) {
                foodCart.getItemList().remove(item);
            }
        }

        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getItemList().contains(item)) {
                restaurant.getItemList().remove(item);
            }
        }

        itemRepository.delete(item);

        return "Item deleted";
    }

}
