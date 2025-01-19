package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.exception.FoodCartException;
import com.jolyvert.deliveryapp.exception.OrderInfoException;
import com.jolyvert.deliveryapp.model.OrderInfo;

public interface OrderService {
    public OrderInfo createOrder(int cartId) throws OrderInfoException, FoodCartException;
}
