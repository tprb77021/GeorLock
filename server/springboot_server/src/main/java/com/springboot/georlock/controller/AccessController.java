package com.springboot.georlock.controller;


import com.springboot.georlock.dto.Login;
import com.springboot.georlock.svc.AccessService;
import com.springboot.georlock.svc.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class AccessController {
    @Autowired
    AccessService accessService;




    @RequestMapping("/access")
    public ModelAndView access() throws Exception {
        System.out.println("access");
        ModelAndView mav=new ModelAndView("access");
        List<Login> userlist= accessService.getAll();
        mav.addObject("userlist",userlist);
        return mav;
    }


}
