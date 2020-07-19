package com.springboot.georlock.controller;

import com.springboot.georlock.dto.SvDTO;
import com.springboot.georlock.service.SvService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValveController {
    @Autowired
    SvService svService;

    @GetMapping("/q")
    public List<SvDTO> svDTOList() throws Exception {
        System.out.println("q");
        return svService.getValue();
    }


}

