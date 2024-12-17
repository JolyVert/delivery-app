package com.jolyvert.deliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jolyvert.deliveryapp.model.FoodCart;

public interface FoodCartRepository extends JpaRepository<FoodCart, Long> {
    public FoodCart findByCustomerId(int customerId);
}
