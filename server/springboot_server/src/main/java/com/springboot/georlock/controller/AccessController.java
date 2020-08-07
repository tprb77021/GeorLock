package com.springboot.georlock.controller;


import com.springboot.georlock.dto.Login;
import com.springboot.georlock.svc.AccessService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


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
    public ModelAndView accessSearch( String textSearch) throws Exception {
        ModelAndView mav = new ModelAndView("access");
        List<Login> userlist = accessService.AccessSearch(textSearch); // 웹에 나타낼 검색된 회원 정보(출입 권한) 조회
        mav.addObject("userlist", userlist);
        return mav;
    }



    @RequestMapping("/accessdelete")      //회원 정보(출입 권한) 삭제 기능
    public String accessdelete(@RequestParam String empNo) throws Exception {
        accessService.Accessdelete(empNo);  //회원 정보 삭제
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
        accessService.AccessUpdate(login);  //회원 정보 수정
        return "close";
    }


    ////////////////////////////////

}
