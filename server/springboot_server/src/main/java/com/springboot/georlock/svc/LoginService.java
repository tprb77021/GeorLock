package com.springboot.georlock.svc;


import com.springboot.georlock.dto.Login;
import com.springboot.georlock.mapper.LoginMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
}
