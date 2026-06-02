package com.example.gasworks_api.customer;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface CustomerMapper {
    @Select("SELECT id, name, address, contact, created_at AS createdAt, updated_at AS updatedAt FROM customers")
    List<Customer> findAll();
}