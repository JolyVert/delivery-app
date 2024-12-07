package com.jolyvert.deliveryapp.repository;

import com.jolyvert.deliveryapp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByRestaurantName(String restaurantName);
    boolean existsByTelephoneNumber(String telephoneNumber);
}
