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


    public List<Enteremp> getEnterEmp() throws Exception {
        List<Enteremp> record = recordMapper.getEnterempAll();

        for (int i = 0; i < record.size(); i++) {
            Enteremp emp = new Enteremp();
            emp = record.get(i);
            emp.setIntime(emp.getIntime().substring(0, 4) + "-" + emp.getIntime().substring(4, 6) + "-" + emp.getIntime().substring(6, 8) + " " + emp.getIntime().substring(8, 10) + ":" + emp.getIntime().substring(10, 12));
            record.set(i, emp);
        }
        return record;
    }

    public List<Enteremp> getRecordSearch(Dates dates) throws Exception {
        final String DATE_MAX = "999999999999";
        final String DATE_MIN = "000000000000";
        final String DATE_START_SECOND = "0000";
        final String DATE_END_SECOND = "9999";
        final String DATE_END_SECOND2 = "000000009999";

        dates.setStartDate(dates.getStartDate().replace("-", "") + DATE_START_SECOND);
        dates.setEndDate(dates.getEndDate().replace("-", "") + DATE_END_SECOND);
        dates.setTextSearch("%" + dates.getTextSearch() + "%");

        if (dates.getStartDate().equals(DATE_START_SECOND)) {
            dates.setStartDate(DATE_MIN);
        }

        if (dates.getEndDate().equals(DATE_END_SECOND) || dates.getEndDate().equals(DATE_END_SECOND2)) {
            dates.setEndDate(DATE_MAX);
        }

        List<Enteremp> record = recordMapper.getSearch(dates);
        Enteremp emp = new Enteremp();
        for (int i = 0; i < record.size(); i++) {
            emp = record.get(i);
            emp.setIntime(emp.getIntime().substring(0, 4) + "-" + emp.getIntime().substring(4, 6) + "-" + emp.getIntime().substring(6, 8) + " " + emp.getIntime().substring(8, 10) + ":" + emp.getIntime().substring(10, 12));
            record.set(i, emp);
        }

        return record;
    }
}
