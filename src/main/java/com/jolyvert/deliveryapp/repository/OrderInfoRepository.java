package com.jolyvert.deliveryapp.repository;

import com.jolyvert.deliveryapp.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {

}
