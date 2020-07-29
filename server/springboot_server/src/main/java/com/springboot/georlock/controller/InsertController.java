package com.springboot.georlock.controller;

import com.springboot.georlock.dto.Login;
import com.springboot.georlock.svc.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InsertController {
    //등록 컨트롤러
    
    @Autowired
    InsertService insertService;

    @RequestMapping("/insert_btn")      //등록 페이지 이동
    public ModelAndView insertform() throws Exception {
        ModelAndView mav = new ModelAndView("insert");
        mav.addObject("empuser", insertService.emplist());
        return mav;
    }

    @RequestMapping("/empSearch")      //등록 페이지 검색 기능
    public ModelAndView empSearch(@RequestParam String textSearch) throws Exception {
        ModelAndView mav = new ModelAndView("insert");
        mav.addObject("empuser", insertService.empSearch(textSearch));
        return mav;
    }

    @RequestMapping("/accessinsert")      //등록 기능
    public String accessinsert(Login login) throws Exception {
        insertService.Accessinsert(login);
        return "redirect:access";
    }
}
