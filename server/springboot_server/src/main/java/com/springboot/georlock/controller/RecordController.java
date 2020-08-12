package com.springboot.georlock.controller;


import com.springboot.georlock.dto.Dates;
import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.svc.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class RecordController {
    @Autowired
    RecordService recordService;

    @RequestMapping("/record")  //출입 기록 페이지 이동
    public ModelAndView record() throws Exception {
        ModelAndView mav = new ModelAndView("record");
        List<Enteremp> list = recordService.getEnterEmp();   //출입 기록 조회
        mav.addObject("recordlist", list);
        return mav;
    }


    @RequestMapping("/recordSearch")  //출입 기록 페이지 검색 기능
    public ModelAndView recordSearch(Dates dates) throws Exception {
        ModelAndView mav = new ModelAndView("record");
        List<Enteremp> list = recordService.getRecordSearch(dates);  //출입 기록 검색
        mav.addObject("recordlist", list);
        return mav;
    }


}
