package com.jolyvert.deliveryapp.controller;


import com.jolyvert.deliveryapp.exception.CustomerException;
import com.jolyvert.deliveryapp.service.CustomerService;
import com.jolyvert.deliveryapp.service.CustomerServiceImpl;
import com.jolyvert.deliveryapp.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getCustomers(Model model) {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) throws CustomerException {
        return ResponseEntity.ok(customerService.deleteCustomer(customerId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) throws CustomerException {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, customer));
    }
}
