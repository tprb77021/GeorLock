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
    @Autowired
    InsertService insertService;

    @RequestMapping("/insert_btn")
    public ModelAndView insertform() throws Exception {
        ModelAndView mav = new ModelAndView("insert");
        mav.addObject("empuser", insertService.emplist());
        return mav;
    }

    @RequestMapping("/empSearch")
    public ModelAndView empSearch(@RequestParam String textSearch) throws Exception {
        ModelAndView mav = new ModelAndView("insert");
        mav.addObject("empuser", insertService.empSearch(textSearch));
        return mav;
    }

    @RequestMapping("/accessinsert")
    public String accessinsert(Login login) throws Exception {
        System.out.println("a :" + login);
        insertService.Accessinsert(login);
        return "redirect:access";
    }
}
