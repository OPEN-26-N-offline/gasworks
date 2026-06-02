package com.example.gasworks_api.customer;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Customer {
    private Long id;
    private String name;
    private String address;
    private String contact;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}