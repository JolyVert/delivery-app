package com.jolyvert.deliveryapp.repository;

import com.jolyvert.deliveryapp.model.FoodCart;
import com.jolyvert.deliveryapp.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
    Optional<OrderInfo> findByFoodCart(FoodCart foodCart);
}
