package com.springboot.georlock.svc;

import com.springboot.georlock.dto.Login;
import com.springboot.georlock.mapper.InsertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsertService {
    @Autowired
    InsertMapper insertMapper;

    final String NFCPATTERN = "0000FF078069FFFFFFFFFFFF";

    public String accessInsert(Login login) throws Exception {
        login.setUserPw(login.getEmpNo());
        login.setNfc(login.getEmpNo() + NFCPATTERN);
        insertMapper.insert(login);
        System.out.println(login.getNfc());
        return login.getNfc();
    }

    public List<Login> empList() throws Exception {
        return insertMapper.emplist();
    }

    public List<Login> empSearch(String textSearch) throws Exception {
        String Search = "%" + textSearch + "%";
        return insertMapper.empSearch(Search);
    }

}
