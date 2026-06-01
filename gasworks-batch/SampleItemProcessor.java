package com.example.gasworks.processor;

import com.example.gasworks.model.InputData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SampleItemProcessor implements ItemProcessor<InputData, String> {

    @Override
    public String process(@NonNull InputData item) {
        log.info("Processing record for facility: {}", item.getFacilityName());
        return String.format("[PROCESSED] ID:%d | FACILITY:%s | USAGE:%d m3", 
                item.getId(), item.getFacilityName().toUpperCase(), item.getUsageAmount());
    }
}