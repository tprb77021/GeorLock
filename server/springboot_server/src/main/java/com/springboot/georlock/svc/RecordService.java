package com.springboot.georlock.svc;

import com.springboot.georlock.dto.Dates;
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

    public List<Enteremp> getRecordSearch(Dates dates) throws Exception{
        dates.setStartDate(dates.getStartDate().replace("-","")+"0000");
        dates.setEndDate(dates.getEndDate().replace("-","")+"9999");
        System.out.println("wqdas"+dates.getTextSearch());
        dates.setTextSearch("%"+dates.getTextSearch()+"%");

        return recordMapper.getSearch(dates);
    }
}
