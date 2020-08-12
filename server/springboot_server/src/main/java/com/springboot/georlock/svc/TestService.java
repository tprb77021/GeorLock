package com.springboot.georlock.svc;

import java.util.List;

import com.springboot.georlock.dto.Login;
import com.springboot.georlock.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestService {

    @Autowired
    TestMapper testMapper;

    public List<Login> getAll() throws Exception {
        return testMapper.getAll();
    }
}
