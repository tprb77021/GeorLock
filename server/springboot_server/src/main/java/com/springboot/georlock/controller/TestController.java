package com.springboot.georlock.controller;

import com.springboot.georlock.dto.Test;
import com.springboot.georlock.svc.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/q")
    public List<Test> query() throws Exception{
        return testService.getAll();
    }


}

