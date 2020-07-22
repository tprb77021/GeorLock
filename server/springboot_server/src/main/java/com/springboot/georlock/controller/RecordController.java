package com.springboot.georlock.controller;


import com.springboot.georlock.dto.Dates;
import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.dto.Login;
import com.springboot.georlock.svc.LoginService;
import com.springboot.georlock.svc.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


@Controller
public class RecordController {
    @Autowired
    RecordService recordService;

    @RequestMapping("/record")
    public ModelAndView record() throws Exception {
        ModelAndView mav=new ModelAndView("record");
        System.out.println("record");
        List<Enteremp> list= recordService.getEnteremp();
        mav.addObject("recordlist",list);

        return mav;
    }



    @RequestMapping("/recordSearch")
    public ModelAndView recordSearch(Dates dates) throws Exception {
        ModelAndView mav=new ModelAndView("record");
        System.out.println("recordddd");
        List<Enteremp> list=recordService.getRecordSearch(dates);
       mav.addObject("recordlist",list);

        return mav;
    }



}
