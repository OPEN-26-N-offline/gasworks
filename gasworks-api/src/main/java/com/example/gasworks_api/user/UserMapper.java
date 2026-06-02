package com.example.gasworks_api.user;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT id, name, email, password, role, created_at AS createdAt, updated_at AS updatedAt FROM users")
    List<User> findAll();

    @Select("SELECT id, name, email, password, role, created_at AS createdAt, updated_at AS updatedAt FROM users WHERE id = #{id}")
    User findById(Long id);

    @Insert("INSERT INTO users (name, email, password, role) VALUES (#{name}, #{email}, #{password}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE users SET name = #{name}, email = #{email}, password = #{password}, role = #{role} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void delete(Long id);
}