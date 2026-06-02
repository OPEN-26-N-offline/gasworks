package com.example.gasworks_api.meter_readings;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeterReadingsService {

    private final MeterReadingsMapper meterReadingsMapper;

    public List<MeterReadings> getAllReadings() {
        return meterReadingsMapper.findAll();
    }

    public MeterReadings getReadingById(Long id) {
        return meterReadingsMapper.findById(id);
    }

    @Transactional
    public void createReading(MeterReadings meterReading) {
        meterReadingsMapper.insert(meterReading);
    }

    @Transactional
    public void updateReading(MeterReadings meterReading) {
        meterReadingsMapper.update(meterReading);
    }

    @Transactional
    public void deleteReading(Long id) {
        meterReadingsMapper.delete(id);
    }
}