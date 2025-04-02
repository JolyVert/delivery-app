package com.jolyvert.deliveryapp.controller;

import com.jolyvert.deliveryapp.exception.FoodCartException;
import com.jolyvert.deliveryapp.exception.OrderInfoException;
import com.jolyvert.deliveryapp.model.FoodCart;
import com.jolyvert.deliveryapp.model.OrderInfo;
import com.jolyvert.deliveryapp.model.User;
import com.jolyvert.deliveryapp.repository.FoodCartRepository;
import com.jolyvert.deliveryapp.service.FoodCartService;
import com.jolyvert.deliveryapp.service.OrderService;
import com.jolyvert.deliveryapp.service.OrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final FoodCartRepository foodCartRepository;

    public OrderController(OrderService orderService, FoodCartRepository foodCartRepository) {
        this.orderService = orderService;
        this.foodCartRepository = foodCartRepository;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/createOrder")
    public ResponseEntity<OrderInfo> createOrder() throws OrderInfoException, FoodCartException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        FoodCart foodCart = foodCartRepository.findByCustomerId(currentUser.getId());
        return ResponseEntity.ok(orderService.createOrder(foodCart.getCartId()));
    }

}
