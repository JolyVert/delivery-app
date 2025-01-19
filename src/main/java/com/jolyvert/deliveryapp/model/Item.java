package com.jolyvert.deliveryapp.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @Column(length = 50, nullable = false)
    private String itemName;

    @Column(length = 200, nullable = false)
    private String itemDescription;

    //@Column(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private Category category;
}
