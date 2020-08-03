package com.springboot.georlock.controller;

import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.svc.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RpiController {
    @Autowired
    LoginService loginService;

    @GetMapping( value = "/dooropen" )
    public String openlist(String cardValue) throws Exception{
        System.out.println(cardValue);
        String log=loginService.doorOpenTry(cardValue);
        return log;
    }



}
