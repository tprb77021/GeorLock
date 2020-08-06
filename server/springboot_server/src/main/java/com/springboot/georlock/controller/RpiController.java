package com.springboot.georlock.controller;

import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.svc.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RpiController {
    @Autowired
    LoginService loginService;

    @GetMapping( "/dooropen" )
    public String openlist(String cardValue) throws Exception{
        System.out.println(cardValue);
        String log=loginService.doorOpenTry(cardValue);
        return log;
    }
    @GetMapping( "/insertnfc" )
    public String insertnfc() throws Exception{
        String log=loginService.insertnfc();
        return log;
    }



}
