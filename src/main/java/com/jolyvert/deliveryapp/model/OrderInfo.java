package com.jolyvert.deliveryapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orderInfo")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @OneToOne(cascade = CascadeType.ALL)
    private FoodCart foodCart;

    private LocalDateTime orderDate;

    boolean isAccepted = false;

    boolean isReady = false;



}
