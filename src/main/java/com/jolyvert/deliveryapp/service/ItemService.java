package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.exception.ItemException;
import com.jolyvert.deliveryapp.exception.RestaurantException;
import com.jolyvert.deliveryapp.model.Item;

import java.util.List;

public interface ItemService {
    public List<Item> addItem(String restaurantName, Item item) throws RestaurantException;
    public String deleteItem(int itemId) throws ItemException;
    public Item updateItem(int itemId, Item item) throws ItemException;
    public List<Item> getItemList(String restaurantName) throws RestaurantException;
    public Item getItem(int itemId) throws ItemException;
}
