package com.jolyvert.deliveryapp.service;


import com.jolyvert.deliveryapp.dto.LoginDto;
import com.jolyvert.deliveryapp.dto.RegisterDto;
import com.jolyvert.deliveryapp.exception.LoginException;
import com.jolyvert.deliveryapp.model.CurrentLoginSession;
import com.jolyvert.deliveryapp.model.Customer;
import com.jolyvert.deliveryapp.model.Restaurant;
import com.jolyvert.deliveryapp.model.User;
import com.jolyvert.deliveryapp.model.enums.Role;
import com.jolyvert.deliveryapp.repository.CustomerRepository;
import com.jolyvert.deliveryapp.repository.SessionRepository;
import com.jolyvert.deliveryapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthenticationService {
    private final CustomerRepository customerRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final FoodCartService foodCartService;

    public AuthenticationService(CustomerRepository customerRepository, SessionRepository sessionRepository, UserRepository userRepository, FoodCartService foodCartService) {
        this.customerRepository = customerRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.foodCartService = foodCartService;
    }

    public User register(RegisterDto registerDto) throws LoginException {

        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new LoginException("User already exists");
        }

        if(registerDto.getRole().equals(Role.CUSTOMER)) {
            Customer customer = new Customer();
            customer.setName(registerDto.getName());
            customer.setEmail(registerDto.getEmail());
            customer.setTelephoneNumber(registerDto.getTelephoneNumber());
            customer.setPassword(registerDto.getPassword());
            customer.setRole(Role.CUSTOMER);
            customer.setAddress(registerDto.getAddress());
            foodCartService.createFoodCart(customer);
            return userRepository.save(customer);
        } else if(registerDto.getRole().equals(Role.RESTAURANT)) {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantName(registerDto.getName());
            restaurant.setEmail(registerDto.getEmail());
            restaurant.setTelephoneNumber(registerDto.getTelephoneNumber());
            restaurant.setPassword(registerDto.getPassword());
            restaurant.setRole(Role.RESTAURANT);
            restaurant.setAddress(registerDto.getAddress());
            return userRepository.save(restaurant);
        } else {
            throw new LoginException("User must be either CUSTOMER or RESTAURANT");
        }
    }

    public CurrentLoginSession login(LoginDto loginDto) throws LoginException {

        Customer customer = customerRepository.findByEmail(loginDto.getEmail());

        if(customer == null) {
            throw new LoginException("User not found");
        }

        if(sessionRepository.findById(customer.getId()).isPresent()) {
            throw new LoginException("Already logged in");
        }

        if(customer.getPassword().equals(loginDto.getPassword())) {

            String key = UUID.randomUUID().toString();
            CurrentLoginSession currentLoginSession = new CurrentLoginSession(customer.getId(), key, LocalDateTime.now());

            return sessionRepository.save(currentLoginSession);


        } else {
            throw new LoginException("Wrong password");
        }
    }


    public String logout(String key) throws LoginException {

        CurrentLoginSession currentLoginSession = sessionRepository.findByUuid(key);

        if(currentLoginSession == null) {
            throw new LoginException("User not logged in");
        }
        sessionRepository.delete(currentLoginSession);

        return "Logged out";
    }
}
