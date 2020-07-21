package com.springboot.georlock.svc;

import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    RecordMapper recordMapper;


    public List<Enteremp> getEnteremp() throws Exception{
        return recordMapper.getEnterempAll();
    }
}
