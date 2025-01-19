package com.jolyvert.deliveryapp.controller;

import com.jolyvert.deliveryapp.exception.FoodCartException;
import com.jolyvert.deliveryapp.exception.OrderInfoException;
import com.jolyvert.deliveryapp.model.OrderInfo;
import com.jolyvert.deliveryapp.service.OrderService;
import com.jolyvert.deliveryapp.service.OrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder/{cartId}")
    public ResponseEntity<OrderInfo> createOrder(@PathVariable int cartId) throws OrderInfoException, FoodCartException {
        return ResponseEntity.ok(orderService.createOrder(cartId));
    }

}
