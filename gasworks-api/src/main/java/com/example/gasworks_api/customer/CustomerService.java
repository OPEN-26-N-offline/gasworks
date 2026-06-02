package com.example.gasworks_api.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;

    public List<Customer> getAllCustomers() {
        return customerMapper.findAll();
    }
}