package com.jolyvert.deliveryapp.repository;

import com.jolyvert.deliveryapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {


}
