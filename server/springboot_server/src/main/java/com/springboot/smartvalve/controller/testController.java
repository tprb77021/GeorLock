package com.springboot.smartvalve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class testController {
    @RequestMapping("/")
    public String test() {
        System.out.println("test");
        return "test";
    }
}
