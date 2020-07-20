package com.springboot.georlock.svc;


import com.springboot.georlock.dto.Login;
import com.springboot.georlock.mapper.LoginMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    LoginMapper loginMapper;

    public boolean Login(String empNo,String userPw) throws Exception{
        boolean loginsuccess= false;
        Login login=new Login();
                login.setEmpNo(empNo);
                login.setUserPw(userPw);
      if(loginMapper.Login(login)!=null){
          loginsuccess=true;
      }

        return loginsuccess;
    }
}
