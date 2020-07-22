package com.springboot.georlock.controller;

import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.dto.Login;
import com.springboot.georlock.dto.Test;
import com.springboot.georlock.svc.AccessService;
import com.springboot.georlock.svc.LoginService;
import com.springboot.georlock.svc.RecordService;
import com.springboot.georlock.svc.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    LoginService loginService;
    @Autowired
    RecordService recordService;
    @Autowired
    AccessService accessService;

    @GetMapping( value = "/q" )
    public List<Login> query() throws Exception{
        return testService.getAll();
    }
    @GetMapping( value = "/openlist" )
    public List<Enteremp> openlist() throws Exception{
        return recordService.getEnteremp();
    }
    @GetMapping( value = "/accesslist" )
    public List<Login> accesslist() throws Exception{
        return testService.getAll();
    }

    @GetMapping("/login")
    public String login(String empNo, String userPw) throws Exception{
        System.out.println("android login strat");
        String log="1성공";
        if(!loginService.Login(empNo,userPw)){
            log="0실패";
        }
        System.out.println("login end");
        return log;
    }
    @GetMapping("/delete")
    public void delete(String empNo) throws Exception{
        System.out.println("android delete");
        accessService.Accessdelete(empNo);
    }

    @GetMapping("/update")
    public void update(String empNo, String intime, String outtime) throws Exception{
        System.out.println("android update");
        Login login=new Login();
        login.setEmpNo(empNo);
        login.setIntime(intime);
        login.setOuttime(outtime);
        accessService.AccessUpdate(login);
    }

}

