package com.example.gasworks_api.meter_readings;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/meter-readings")
@RequiredArgsConstructor
public class MeterReadingController {

    private final MeterReadingsService meterReadingsService;

    @GetMapping
    public List<MeterReadings> getAllReadings() {
        return meterReadingsService.getAllReadings();
    }

    @GetMapping("/{id}")
    public MeterReadings getReadingById(@PathVariable Long id) {
        return meterReadingsService.getReadingById(id);
    }

    @PostMapping
    public void createReading(@RequestBody MeterReadings meterReading) {
        meterReadingsService.createReading(meterReading);
    }

    @PutMapping("/{id}")
    public void updateReading(@PathVariable Long id, @RequestBody MeterReadings meterReading) {
        meterReading.setId(id);
        meterReadingsService.updateReading(meterReading);
    }

    @DeleteMapping("/{id}")
    public void deleteReading(@PathVariable Long id) {
        meterReadingsService.deleteReading(id);
    }
}