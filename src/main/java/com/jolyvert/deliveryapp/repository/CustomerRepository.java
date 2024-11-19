package com.jolyvert.deliveryapp.repository;

import com.jolyvert.deliveryapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByTelephoneNumber(String telephoneNumber);
}
