package com.springboot.georlock.controller;


import com.springboot.georlock.svc.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
    //로그인 컨트롤러
    @Autowired
    LoginService loginService;
    final String EMPNO="empNo";
    final String USERPW="userPw";

    @RequestMapping("/")    // 로그인 페이지 이동
    public String loginForm(HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();    // 쿠키 확인
        String page = "login";
        if (cookies != null) {      //쿠키가 있으면 실행
            String empNo = "";
            String userPw = "";
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(EMPNO)) {
                    empNo = cookies[i].getValue();
                } else if (cookies[i].getName().equals(USERPW)) {
                    userPw = cookies[i].getValue();
                }
            }
            if (loginService.Login(empNo, userPw) == null) {     //쿠키값으로 로그인 시도
                page = "redirect:access";
            }
        }
        return page;
    }

    @RequestMapping("/login")       //로그인 기능
    public ModelAndView login(String empNo, String userPw, boolean autoLogin, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView("redirect:/access");
        if (loginService.Login(empNo, userPw).getEmpNo() == null) {         //로그인 확인
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "로그인 실패");
        } else if (autoLogin) {         //자동 로그인 체크 여부
            loginService.autoLogin(empNo, userPw, response);
        }
        return modelAndView;
    }


    @RequestMapping("/logout")      //로그아웃 기능
    public String logout(HttpServletResponse response, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {          //쿠키 삭제 기능
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0);
                cookies[i].setPath("/");
                response.addCookie(cookies[i]);
            }
        }
        return "redirect:/";
    }


}
