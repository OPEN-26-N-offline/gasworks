package com.example.gasworks_api.meter_readings;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface MeterReadingsMapper {

    @Select("SELECT id, customer_id AS customerId, user_id AS userId, reading_date AS readingDate, `value`, created_at AS createdAt, updated_at AS updatedAt FROM meter_readings")
    List<MeterReadings> findAll();

    @Select("SELECT id, customer_id AS customerId, user_id AS userId, reading_date AS readingDate, `value`, created_at AS createdAt, updated_at AS updatedAt FROM meter_readings WHERE id = #{id}")
    MeterReadings findById(Long id);

    @Insert("INSERT INTO meter_readings (customer_id, user_id, reading_date, `value`) VALUES (#{customerId}, #{userId}, #{readingDate}, #{value})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(MeterReadings meterReading);

    @Update("UPDATE meter_readings SET customer_id = #{customerId}, user_id = #{userId}, reading_date = #{readingDate}, `value` = #{value} WHERE id = #{id}")
    void update(MeterReadings meterReading);

    @Delete("DELETE FROM meter_readings WHERE id = #{id}")
    void delete(Long id);
}