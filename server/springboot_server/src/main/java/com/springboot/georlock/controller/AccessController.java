package com.springboot.georlock.controller;


import com.springboot.georlock.dto.Login;
import com.springboot.georlock.svc.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;


@Controller
public class AccessController {
    //출입 권한 컨트롤러

    @Autowired
    AccessService accessService;

    @RequestMapping("/access")      //회원 정보(출입 권한) 관리 페이지 이동
    public ModelAndView access() throws Exception {
        ModelAndView mav = new ModelAndView("access");
        List<Login> userlist = accessService.getAll();      // 웹에 나타낼 회원 정보(출입 권한) 조회
        mav.addObject("userlist", userlist);
        return mav;
    }


    @RequestMapping("/accessSearch")      // 회원 정보(출입 권한 관리) 검색 기능(사번 or 이름)
    public ModelAndView accessSearch(String textSearch) throws Exception {
        ModelAndView mav = new ModelAndView("access");
        List<Login> userlist = accessService.accessSearch(textSearch); // 웹에 나타낼 검색된 회원 정보(출입 권한) 조회
        mav.addObject("userlist", userlist);
        return mav;
    }


    @RequestMapping("/accessDelete")      //회원 정보(출입 권한) 삭제 기능
    public String accessDelete(@RequestParam String empNo) throws Exception {
        accessService.accessDelete(empNo);  //회원 정보 삭제
        return "redirect:access";
    }


    @RequestMapping("/accessModity")     //출입 시간 수정 페이지 이동
    public ModelAndView accessModity(@RequestParam String empNo, @RequestParam String username) throws Exception {
        ModelAndView mav = new ModelAndView("modify");
        mav.addObject("empNo", empNo);
        mav.addObject("username", username);
        return mav;
    }

    @RequestMapping("/accessUpdate")     //출입 시간 수정 기능
    public String accessUpdate(Login login) throws Exception {
        accessService.accessUpdate(login);  //회원 정보 수정
        return "close";
    }


}
