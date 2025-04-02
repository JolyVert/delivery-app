package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.exception.FoodCartException;
import com.jolyvert.deliveryapp.exception.OrderInfoException;
import com.jolyvert.deliveryapp.model.FoodCart;
import com.jolyvert.deliveryapp.model.OrderInfo;
import com.jolyvert.deliveryapp.repository.FoodCartRepository;
import com.jolyvert.deliveryapp.repository.OrderInfoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderInfoRepository orderInfoRepository;

    private final FoodCartRepository foodCartRepository;

    public OrderServiceImpl(OrderInfoRepository orderInfoRepository, FoodCartRepository foodCartRepository) {
        this.orderInfoRepository = orderInfoRepository;
        this.foodCartRepository = foodCartRepository;
    }

    public OrderInfo createOrder(long cartId) throws OrderInfoException, FoodCartException{

        FoodCart foodCart = foodCartRepository.findById(cartId).orElseThrow(()->new FoodCartException("FoodCart not found"));

        if(orderInfoRepository.findByFoodCart(foodCart).isPresent()) {
            throw new OrderInfoException("You already have an active order");
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderDate(LocalDateTime.now());
        orderInfo.setFoodCart(foodCart);
        return orderInfoRepository.save(orderInfo);
    }

}
