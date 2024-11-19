package com.jolyvert.deliveryapp.dto;

import com.jolyvert.deliveryapp.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRestaurantDto {
    private String restaurantName;

    private String telephoneNumber;

    private Address address;
}
