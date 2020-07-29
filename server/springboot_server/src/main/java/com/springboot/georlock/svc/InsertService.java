package com.springboot.georlock.svc;

import com.springboot.georlock.dto.Login;
import com.springboot.georlock.mapper.AccessMapper;
import com.springboot.georlock.mapper.InsertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InsertService {
    @Autowired
    InsertMapper insertMapper;

    public void Accessinsert(Login login) throws Exception {
        login.setUserPw(login.getEmpNo());
        insertMapper.insert(login);
    }

    public List<Login> emplist() throws Exception {
        return  insertMapper.emplist();
    }

    public List<Login> empSearch(String textSearch) throws Exception{
        String Search = "%"+textSearch+"%";
        return insertMapper.empSearch(Search);
    }
}
