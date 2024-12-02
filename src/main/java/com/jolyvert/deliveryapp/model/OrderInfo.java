package com.jolyvert.deliveryapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orderInfo")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    //@OneToOne(cascade = CascadeType.ALL)
    //private FoodCart foodCart;



}
