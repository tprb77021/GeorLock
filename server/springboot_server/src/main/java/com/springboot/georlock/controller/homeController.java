package com.springboot.georlock.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
    @RequestMapping("/")
    public String test() {
        System.out.println("test");
        return "login";
    }
}
