package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.exception.FoodCartException;
import com.jolyvert.deliveryapp.exception.OrderInfoException;
import com.jolyvert.deliveryapp.model.OrderInfo;
import com.jolyvert.deliveryapp.repository.FoodCartRepository;
import com.jolyvert.deliveryapp.repository.OrderInfoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderInfoRepository orderInfoRepository;

    private FoodCartRepository foodCartRepository;

    public OrderServiceImpl(OrderInfoRepository orderInfoRepository, FoodCartRepository foodCartRepository) {
        this.orderInfoRepository = orderInfoRepository;
        this.foodCartRepository = foodCartRepository;
    }

    public OrderInfo createOrder(int cartId) throws OrderInfoException, FoodCartException{
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderDate(LocalDateTime.now());
        orderInfo.setFoodCart(foodCartRepository.findById((long)cartId).orElseThrow(() -> new FoodCartException("FoodCart not found")));
        orderInfo.setReady(false);
        return orderInfoRepository.save(orderInfo);
    }

}
