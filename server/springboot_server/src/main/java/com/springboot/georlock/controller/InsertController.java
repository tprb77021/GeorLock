package com.springboot.georlock.controller;

import com.springboot.georlock.dto.Login;
import com.springboot.georlock.svc.InsertService;
import com.springboot.georlock.svc.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InsertController {
    //등록 컨트롤러

    @Autowired
    InsertService insertService;
    @Autowired
    LoginService loginService;

    @RequestMapping("/insert_btn")      //등록 페이지 이동
    public ModelAndView insertForm() throws Exception {
        ModelAndView mav = new ModelAndView("insert");
        mav.addObject("empuser", insertService.empList());  //등록 되지 않은 회원 정보 조회
        return mav;
    }

    @RequestMapping("/empSearch")      //등록 페이지 검색 기능
    public ModelAndView empSearch(@RequestParam String textSearch) throws Exception {
        ModelAndView mav = new ModelAndView("insert");
        mav.addObject("empuser", insertService.empSearch(textSearch)); //등록 되지 않은 회원 정보 검색
        return mav;
    }

    @RequestMapping("/accessInsert")      //등록 및 nfc 값 조회
    public String accessInsert(Login login) throws Exception {
        String nfc = insertService.accessInsert(login); //등록 및 nfc 값 조회
        setNfc(nfc);   //nfc값 셋팅
        return "redirect:access";
    }

    //nfc 등록을 위한 메소드
    public void setNfc(String nfc) throws Exception {
        loginService.setNfc(nfc); //nfc값 셋팅
    }
}
