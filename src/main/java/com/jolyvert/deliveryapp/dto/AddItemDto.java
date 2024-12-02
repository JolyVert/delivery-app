package com.jolyvert.deliveryapp.dto;

import com.jolyvert.deliveryapp.model.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemDto {

    private int id;

    private Item item;

}
