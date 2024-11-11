package com.jolyvert.deliveryapp.controller;


import com.jolyvert.deliveryapp.service.CustomerService;
import com.jolyvert.deliveryapp.dto.RegisterDto;
import com.jolyvert.deliveryapp.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getCustomers(Model model) {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> addCustomer(@RequestBody RegisterDto registerDto) {
        Customer customer = customerService.createCustomer(registerDto);

        return ResponseEntity.ok(customer);
    }
}
