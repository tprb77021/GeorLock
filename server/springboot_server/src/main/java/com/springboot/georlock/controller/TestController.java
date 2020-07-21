package com.springboot.georlock.controller;

import com.springboot.georlock.dto.Login;
import com.springboot.georlock.dto.Test;
import com.springboot.georlock.svc.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping( value = "/q" )
    public List<Login> query() throws Exception{
        return testService.getAll();
    }


}

