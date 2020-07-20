package com.springboot.georlock.controller;


import com.springboot.georlock.dto.Test;
import com.springboot.georlock.svc.LoginService;
import com.springboot.georlock.svc.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class LoginController {
    @Autowired
    LoginService loginService;




    @RequestMapping("/loginform")
    public String test() {
        System.out.println("loginForm");
        return "login";
    }

    @RequestMapping("/login")
    public ModelAndView login(String empNo,String userPw) throws Exception{
        System.out.println("login strat");
        ModelAndView modelAndView = new ModelAndView("access");
        if(!loginService.Login(empNo,userPw)){
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "로그인 실패");
        }
        System.out.println("login end");
        return modelAndView;
    }



    @RequestMapping("/access")
    public String access() {
        System.out.println("access");
        return "access";
    }

    @RequestMapping("/record")
    public String record() {
        System.out.println("record");
        return "record";
    }


    @RequestMapping("/logout")
    public String logout() {
        System.out.println("logout");
        return "redirect:/loginform";

    }



}
