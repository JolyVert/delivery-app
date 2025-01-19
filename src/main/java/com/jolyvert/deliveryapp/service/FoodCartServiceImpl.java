package com.jolyvert.deliveryapp.service;

import com.jolyvert.deliveryapp.exception.FoodCartException;
import com.jolyvert.deliveryapp.model.Customer;
import com.jolyvert.deliveryapp.model.FoodCart;
import com.jolyvert.deliveryapp.model.Item;
import com.jolyvert.deliveryapp.repository.FoodCartRepository;
import com.jolyvert.deliveryapp.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FoodCartServiceImpl implements FoodCartService {

    private final FoodCartRepository foodCartRepository;
    private final ItemRepository itemRepository;

    public FoodCartServiceImpl(FoodCartRepository foodCartRepository, ItemRepository itemRepository) {
        this.foodCartRepository = foodCartRepository;
        this.itemRepository = itemRepository;
    }

    public FoodCart createFoodCart(Customer customer) {

        FoodCart foodCart = new FoodCart();
        foodCart.setCustomer(customer);
        foodCart.setItemList(new ArrayList<Item>());
        return foodCartRepository.save(foodCart);
    }

    public FoodCart addItem(int customerId, int itemId) throws FoodCartException {

        if(!itemRepository.existsById((long)itemId)) {
            throw new FoodCartException("Item does not exist");
        }

        FoodCart cart = foodCartRepository.findByCustomerId(customerId);
        cart.getItemList().add(itemRepository.findById((long)itemId).orElseThrow());
        return foodCartRepository.save(cart);
    }

}
