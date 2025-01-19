package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.exception.FoodCartException;
import com.jolyvert.deliveryapp.model.Customer;
import com.jolyvert.deliveryapp.model.FoodCart;

public interface FoodCartService {
    public FoodCart createFoodCart(Customer customer);
    public FoodCart addItem(int customerId, int itemId) throws FoodCartException;

}
