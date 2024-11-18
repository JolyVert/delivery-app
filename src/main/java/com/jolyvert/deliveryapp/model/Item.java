package com.jolyvert.deliveryapp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(length = 50)
    private String itemName;

    @Column(length = 200)
    private String itemDescription;
}
