package com.springboot.georlock.svc;


import com.springboot.georlock.dto.Login;
import com.springboot.georlock.mapper.AccessMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccessService {

    @Autowired
    AccessMapper accessMapper;

    public List<Login> getAll() throws Exception {
        return accessMapper.getAll();
    }

    public void accessUpdate(Login login) throws Exception {
        accessMapper.update(login);
    }

    public void accessDelete(String empNo) throws Exception {
        accessMapper.delete(empNo);
    }

    public List<Login> accessSearch(String textSearch) throws Exception {
        String Search = "%" + textSearch + "%";
        return accessMapper.Search(Search);
    }


}
