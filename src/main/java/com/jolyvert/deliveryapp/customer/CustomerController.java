package com.jolyvert.deliveryapp.customer;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> addCustomer(@RequestBody RegisterCustomerDto registerCustomerDto) {
        Customer customer = customerService.createCustomer(registerCustomerDto);

        return ResponseEntity.ok(customer);
    }
}
