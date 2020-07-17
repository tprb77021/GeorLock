package com.springboot.georlock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
    @RequestMapping("/login")
    public String test() {
        System.out.println("login");
        return "login";
    }
}
