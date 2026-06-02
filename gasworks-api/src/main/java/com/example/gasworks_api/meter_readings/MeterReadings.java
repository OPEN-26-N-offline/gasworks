package com.example.gasworks_api.meter_readings;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeterReadings {
    private Long id;
    private Long customerId;
    private Long userId;
    private LocalDate readingDate;
    private BigDecimal value;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}