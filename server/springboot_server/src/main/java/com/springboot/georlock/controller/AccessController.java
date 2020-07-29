package com.springboot.georlock.controller;


import com.springboot.georlock.dto.Login;
import com.springboot.georlock.svc.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;


@Controller
public class AccessController {
    //출입 권한 컨트롤러

    @Autowired
            AccessService accessService;

    @RequestMapping("/access")      //출입 권한 관리 페이지 이동
    public ModelAndView access() throws Exception {
        ModelAndView mav = new ModelAndView("access");
        List<Login> userlist = accessService.getAll();
        mav.addObject("userlist", userlist);
        return mav;
    }


    @RequestMapping("/accessSearch")      //출입 권한 관리 검색 기능(사번 or 이름)
    public ModelAndView accessSearch( String textSearch) throws Exception {
        ModelAndView mav = new ModelAndView("access");
        List<Login> userlist = accessService.AccessSearch(textSearch);
        mav.addObject("userlist", userlist);
        return mav;
    }



    @RequestMapping("/accessdelete")      //출입 권한 삭제 기능
    public String accessdelete(@RequestParam String empNo) throws Exception {
        accessService.Accessdelete(empNo);
        return "redirect:access";
    }


    @RequestMapping("/accessmodity")     //출입 시간 수정 페이지 이동
    public ModelAndView accessmodity(@RequestParam String empNo, @RequestParam String username) throws Exception {
        ModelAndView mav = new ModelAndView("modify");
        mav.addObject("empNo", empNo);
        mav.addObject("username", username);
        return mav;
    }

    @RequestMapping("/accessupdate")     //출입 시간 수정 기능
    public String accessupdate(Login login) throws Exception {
        accessService.AccessUpdate(login);
        return "close";
    }



}
