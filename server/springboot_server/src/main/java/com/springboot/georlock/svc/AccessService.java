package com.springboot.georlock.svc;


import com.springboot.georlock.dto.Login;
import com.springboot.georlock.mapper.AccessMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class AccessService {

    @Autowired
    AccessMapper accessMapper;

    public List<Login> getAll() throws Exception{
        return accessMapper.getAll();
    }


    public void AccessUpdate(Login login) throws Exception {
        accessMapper.update(login);
    }

    public void Accessdelete(String empNo) throws Exception {
        accessMapper.delete(empNo);
    }

    public void Accessinsert(Login login) throws Exception {
        accessMapper.insert(login);
    }

    public List<Login> AccessSearch(String textSearch) throws Exception{
       return accessMapper.Search(textSearch);
    }


}
