package com.springboot.georlock.mapper;

import com.springboot.georlock.dto.Enteremp;

import java.util.List;

public interface RecordMapper {
    public List<Enteremp> getEnterempAll() throws Exception;
}
