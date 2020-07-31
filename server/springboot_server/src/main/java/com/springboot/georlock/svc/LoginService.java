package com.springboot.georlock.svc;


import com.springboot.georlock.dto.Login;
import com.springboot.georlock.mapper.LoginMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class LoginService {

    @Autowired
    LoginMapper loginMapper;

    public Login Login(String empNo,String userPw) throws Exception{

        Login loginsuccess= new Login();
        Login login=new Login();
        login.setEmpNo(empNo);
        login.setUserPw(userPw);
        loginsuccess.setUsertype("0");
      if(loginMapper.Login(login)!=null){
          loginsuccess=loginMapper.Login(login);
      }

        return loginsuccess;
    }

    public void autoLogin(String empNo, String userPw,  HttpServletResponse response) {
        Cookie cookie1 = new Cookie("empNo", empNo);
        cookie1.setMaxAge(-1);
        cookie1.setPath("/");
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("userPw", userPw);
        cookie2.setMaxAge(-1);
        cookie2.setPath("/");
        response.addCookie(cookie2);

    }

    public void userUpdate(String empNo, String userPw) throws Exception{
        Login login=new Login();
        login.setEmpNo(empNo);
        login.setUserPw(userPw);
        loginMapper.userUpdate(login);

    }

    public String getToken(String empNo) {
      return   loginMapper.getToken(empNo);
    }

    public void updateToken(String token,String empNo) throws Exception {
        Login login = new Login();
        login.setToken(token);
        login.setEmpNo(empNo);
        System.out.println(token);
        loginMapper.updateToken(login);
    }
}
