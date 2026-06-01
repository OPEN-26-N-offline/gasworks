package com.example.gasworks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputData {
    private Long id;
    private String facilityName;
    private Integer usageAmount;
}