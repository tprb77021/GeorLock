package com.springboot.georlock.svc;


import com.springboot.georlock.dto.Dates;
import com.springboot.georlock.dto.Login;
import com.springboot.georlock.mapper.LoginMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public Login getToken(String empNo) {
      return   loginMapper.getToken(empNo);
    }

    public void updateToken(String token,String empNo) throws Exception {
        Login login = new Login();
        login.setToken(token);
        login.setEmpNo(empNo);
        System.out.println(token);
        loginMapper.updateToken(login);
    }

    public String getdoor() {
        return loginMapper.getdoor();
    }

    public void setdoor(int i) {
        loginMapper.setdoor(i);
    }

    public String doorOpenTry(String cardValue) {
      Login login = loginMapper.doorOpenTry(cardValue);
      Date time=new Date();
      String log= "0";
      SimpleDateFormat format1 = new SimpleDateFormat ( "HHmm");
      SimpleDateFormat format2 = new SimpleDateFormat ( "yyyyMMddHHmm");
      int time1 = Integer.parseInt(format1.format(time));
       if(time1 >= Integer.parseInt(login.getIntime().replace(":","")) && time1 <= Integer.parseInt(login.getOuttime().replace(":","")) ){
           log="1";
           login.setIntime(format2.format(time));
           loginMapper.enteremp(login);
       }
      return log;
    }
}
