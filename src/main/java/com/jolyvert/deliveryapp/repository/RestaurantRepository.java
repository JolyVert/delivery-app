package com.jolyvert.deliveryapp.repository;

import com.jolyvert.deliveryapp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByTelephoneNumber(String telephoneNumber);
    boolean existsByTelephoneNumber(String telephoneNumber);
}
