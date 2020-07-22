package com.springboot.georlock.mapper;

import com.springboot.georlock.dto.Dates;
import com.springboot.georlock.dto.Enteremp;

import java.util.List;

public interface RecordMapper {
    public List<Enteremp> getEnterempAll() throws Exception;

    public List<Enteremp> getSearch(Dates dates) throws Exception;
}
