package com.springboot.georlock.mapper;

import com.springboot.georlock.dto.Login;

import java.util.List;

public interface TestMapper {
    public List<Login> getAll() throws Exception;
}