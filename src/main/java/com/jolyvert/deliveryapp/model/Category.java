package com.jolyvert.deliveryapp.model;

import com.jolyvert.deliveryapp.model.enums.CategoryName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;

    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

}
