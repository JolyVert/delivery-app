package com.jolyvert.deliveryapp.service;



import com.jolyvert.deliveryapp.exception.OrderInfoException;
import com.jolyvert.deliveryapp.exception.RestaurantException;
import com.jolyvert.deliveryapp.model.OrderInfo;
import com.jolyvert.deliveryapp.model.Restaurant;
import com.jolyvert.deliveryapp.repository.ItemRepository;
import com.jolyvert.deliveryapp.repository.OrderInfoRepository;
import com.jolyvert.deliveryapp.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ItemRepository itemRepository;
    private final OrderInfoRepository orderInfoRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ItemRepository itemRepository, OrderInfoRepository orderInfoRepository) {
        this.restaurantRepository = restaurantRepository;
        this.itemRepository = itemRepository;
        this.orderInfoRepository = orderInfoRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public String deleteRestaurant(Long restaurantId) throws RestaurantException {

        restaurantRepository.delete(restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantException("Restaurant does not exist")));
        return "Restaurant deleted";
    }

    public String acceptOrder(Long OrderId) throws OrderInfoException {
        OrderInfo order = orderInfoRepository.findById(OrderId).orElseThrow(() -> new OrderInfoException("Order does not exist"));
        if(order.isAccepted()) {
            throw new OrderInfoException("Order is already accepted");
        }
        order.setAccepted(true);
        orderInfoRepository.save(order);
        return "Order accepted";
    }






}
