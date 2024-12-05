package com.jolyvert.deliveryapp.controller;


import com.jolyvert.deliveryapp.exception.CustomerException;
import com.jolyvert.deliveryapp.exception.FoodCartException;
import com.jolyvert.deliveryapp.service.CustomerService;
import com.jolyvert.deliveryapp.dto.RegisterCustomerDto;
import com.jolyvert.deliveryapp.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Customer> addCustomer(@RequestBody RegisterCustomerDto registerCustomerDto) throws CustomerException {
        Customer customer = customerService.createCustomer(registerCustomerDto);

        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) throws CustomerException {
        return ResponseEntity.ok(customerService.deleteCustomer(customerId));
    }
}
