package com.jolyvert.deliveryapp.repository;

import com.jolyvert.deliveryapp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
