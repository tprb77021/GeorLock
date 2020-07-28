package com.springboot.georlock.controller;

import com.springboot.georlock.dto.Dates;
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

    @GetMapping( value = "/accessSearch" )
    public List<Login> accessSearch(String search) throws Exception{
        System.out.println(search);
        return  accessService.AccessSearch(search);
    }
    @GetMapping( value = "/openSearch" )
    public List<Enteremp> openSearch(String search,String startDate,String endDate) throws Exception{
        System.out.println(search);
        Dates dates=new Dates();
        dates.setStartDate(startDate);
        dates.setEndDate(startDate);
        dates.setTextSearch(search);
        return  recordService.getRecordSearch(dates);
    }

    @GetMapping("/login")
    public String login(String empNo, String userPw) throws Exception{
        System.out.println("android login strat");
        String log="1성공@"+loginService.Login(empNo,userPw).getIntime()+"@"+loginService.Login(empNo,userPw).getOuttime()+"@"+loginService.Login(empNo,userPw).getEmpNo();
        if(loginService.Login(empNo,userPw).getUsertype().equals("0")){
            log="0실패";
        }
        else if(loginService.Login(empNo,userPw).getUsertype().equals("2")){
            log="2성공@"+loginService.Login(empNo,userPw).getIntime()+"@"+loginService.Login(empNo,userPw).getOuttime()+"@"+loginService.Login(empNo,userPw).getEmpNo();;
        }
        System.out.println("login end");
        return log;
    }

    @GetMapping("/userupdate")
    public void userupdate(String empNo,String userPw,String username) throws Exception{
        System.out.println("android userupdate");
        System.out.println(empNo+userPw+username);
        loginService.userUpdate(empNo,userPw,username);

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

    @GetMapping("/opencall")
    public void opencall(String empNo) throws Exception{
        System.out.println("opencall :"+empNo);
    }

    @GetMapping("/open")
    public String open(String empNo) throws Exception{

        System.out.println("open :"+empNo);
        return  "openSuccess";
    }

}

