package com.springboot.georlock.svc;


import com.springboot.georlock.dto.Login;
import com.springboot.georlock.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Slf4j
@Service
public class LoginService {

    @Autowired
    LoginMapper loginMapper;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Login Login(String empNo, String userPw) throws Exception {

        Login loginsuccess = new Login();
        Login login = new Login();
        login.setEmpNo(empNo);
        login.setUserPw(userPw);
        loginsuccess.setUsertype("0");
        if (loginMapper.Login(login) != null) {
            loginsuccess = loginMapper.Login(login);
        }

        return loginsuccess;
    }

    public void autoLogin(String empNo, String userPw, HttpServletResponse response) {
        Cookie cookie1 = new Cookie("empNo", empNo);
        cookie1.setMaxAge(-1);
        cookie1.setPath("/");
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("userPw", userPw);
        cookie2.setMaxAge(-1);
        cookie2.setPath("/");
        response.addCookie(cookie2);

    }

    public void userUpdate(String empNo, String userPw) throws Exception {
        Login login = new Login();
        login.setEmpNo(empNo);
        login.setUserPw(userPw);
        loginMapper.userUpdate(login);

    }

    public Login getToken(String empNo) {   //토큰값 조회
        return loginMapper.getToken(empNo);
    }

    public void updateToken(String token, String empNo) throws Exception {
        Login login = new Login();
        login.setToken(token);
        login.setEmpNo(empNo);
        System.out.println(token);
        loginMapper.updateToken(login);
    }

    public String getDoor() {   // 현재 개폐 상태 조회
        return loginMapper.getdoor().getDoor();
    }

    public void setdoor(int i, String empNo) {   //도어락 상태 설정 및 출입 기록 등록
        loginMapper.setdoor(i);     //도어락 상태 설정
        if (!(empNo.equals("no"))) {
            Date time = new Date();
            SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmm", Locale.KOREA);
            Login login = loginMapper.selectuser(empNo);
            login.setIntime(format2.format(time));
            loginMapper.enteremp(login);                    //출입 기록 등록
        }
    }

    public void setdoor(int i) {        // 도어락 상태 설정
        loginMapper.setdoor(i);
    }

    public String doorOpenTry(String cardValue) {   // nfc로 도어락 오픈 시도
        Login login = loginMapper.doorOpenTry(cardValue); //nfc로 회원정보 조회
        Date time = new Date();
        String log = "0";
        if (login != null) {
            SimpleDateFormat format1 = new SimpleDateFormat("HHmm", Locale.KOREA);
            SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmm", Locale.KOREA);
            int time1 = Integer.parseInt(format1.format(time));
            if (time1 >= Integer.parseInt(login.getIntime().replace(":", ""))
                    && time1 <= Integer.parseInt(login.getOuttime().replace(":", ""))) {
                //nfc 태그한 시간과 회원의 출입 가능 여부
                log = "1";
                login.setIntime(format2.format(time));
                loginMapper.enteremp(login);                //출입 기록 등록
            }
        }
        return log;
    }

    public void setNfc(String nfc) { //nfc 등록(쓰기) 실행 여부 설정
        loginMapper.setnfc(nfc);
    }

    public String getInsertNfc() {  //nfc 등록(쓰기)값 설정
        String re = loginMapper.getdoor().getInnfc();
        if (!re.equals('0')) {
            return "10x" + re.substring(0, 2) + "0x" + re.substring(2, 4) + "0x" + re.substring(4, 6) + "0x" + re.substring(6, 8);
        }
        return "0";
    }
}
