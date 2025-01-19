package com.jolyvert.deliveryapp.model;

import com.jolyvert.deliveryapp.model.enums.CategoryName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

}
