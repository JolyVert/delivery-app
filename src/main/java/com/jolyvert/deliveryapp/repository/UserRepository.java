package com.jolyvert.deliveryapp.repository;

import com.jolyvert.deliveryapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
