package com.example.gasworks_api.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService; // ★Serviceを呼び出す

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers(); // ★Serviceに仕事を頼む
    }
}