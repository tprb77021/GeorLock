package com.springboot.georlock.controller;


import com.springboot.georlock.dto.Login;
import com.springboot.georlock.svc.AccessService;
import com.springboot.georlock.svc.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class AccessController {
    @Autowired
    AccessService accessService;




    @RequestMapping("/access")
    public ModelAndView access() throws Exception {
        System.out.println("access");
        ModelAndView mav=new ModelAndView("access");
        List<Login> userlist= accessService.getAll();
        mav.addObject("userlist",userlist);
        return mav;
    }


    @RequestMapping("/accessSearch")
    public ModelAndView accessSearch(@RequestParam String textSearch) throws Exception {
        ModelAndView mav=new ModelAndView("access");
        System.out.println("accessSearch");
        List<Login> userlist= accessService.AccessSearch(textSearch);
        mav.addObject("userlist",userlist);
        return mav;
    }

    @RequestMapping("/accessdelete")
    public String accessdelete(@RequestParam String empNo) throws Exception {
        System.out.println("access");
        System.out.println(empNo);
        accessService.Accessdelete(empNo);
        return "redirect:access";
    }

    @RequestMapping("/accessadd")
    public String accessadd() throws Exception {
        System.out.println("accessmodity");
        return ""; //등록페이지 이름 적기
    }

    @RequestMapping("/accessinsert")
    public String accessinsert(@RequestParam Login login) throws Exception {
        System.out.println("accessinsert");
        accessService.Accessinsert(login);


        return "redirect:access";
    }

    @RequestMapping("/accessmodity")
    public ModelAndView accessmodity(@RequestParam String empNo,@RequestParam String username) throws Exception {
        ModelAndView mav=new ModelAndView("modify");
        mav.addObject("empNo",empNo);
        mav.addObject("username",username);
        System.out.println("accessmodity");
        System.out.println(empNo);
        return mav;
    }

    @RequestMapping("/accessupdate")
    public String accessupdate(@RequestParam String empNo,@RequestParam String intime,@RequestParam String outtime) throws Exception {
        System.out.println("accessupdate");
        Login login=new Login();
        login.setEmpNo(empNo);
        login.setIntime(intime);
        login.setOuttime(outtime);
        accessService.AccessUpdate(login);
        return "close";
    }


}
