package com.springboot.georlock.controller;

import com.springboot.georlock.dto.Dates;
import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.dto.Login;
import com.springboot.georlock.svc.AccessService;
import com.springboot.georlock.svc.LoginService;
import com.springboot.georlock.svc.RecordService;
import com.springboot.georlock.svc.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AndroidController {
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

//출입 기록 관련
    @GetMapping( value = "/openlist" )
    public List<Enteremp> openlist() throws Exception{
        return recordService.getEnteremp();
    }

    @GetMapping( value = "/openSearch" )
    public List<Enteremp> openSearch(String search,String startDate,String endDate) throws Exception{
        Dates dates=new Dates();
        dates.setStartDate(startDate);
        dates.setEndDate(startDate);
        dates.setTextSearch(search);
        return  recordService.getRecordSearch(dates);
    }

    //출입 권한 관련
    @GetMapping( value = "/accesslist" )
    public List<Login> accesslist() throws Exception{
        return testService.getAll();
    }

    @GetMapping( value = "/accessSearch" )
    public List<Login> accessSearch(String search) throws Exception{
        return  accessService.AccessSearch(search);
    }

    @GetMapping("/delete")
    public void delete(String empNo) throws Exception{
        accessService.Accessdelete(empNo);
    }

    @GetMapping("/update")
    public void update(String empNo, String intime, String outtime) throws Exception{
        Login login=new Login();
        login.setEmpNo(empNo);
        login.setIntime(intime);
        login.setOuttime(outtime);
        accessService.AccessUpdate(login);
    }

    //로그인
    @GetMapping("/login")
    public String login(String empNo, String userPw) throws Exception{
        Login login=loginService.Login(empNo,userPw);
        String log="1성공@"+login.getIntime()+"@"+login.getOuttime()+"@"+login.getEmpNo();
        if(login.getUsertype().equals("0")){
            log="0실패";
        }
        else if(login.getUsertype().equals("2")){
            log="2성공@"+login.getIntime()+"@"+login.getOuttime()+"@"+login.getEmpNo();;
        }
        return log;
    }

    //개인정보 수정
    @GetMapping("/userupdate")
    public void userupdate(String empNo,String userPw) throws Exception{
        loginService.userUpdate(empNo,userPw);
    }


    //출입 요청
    @GetMapping("/opencall")
    public void opencall(String empNo) throws Exception{
        System.out.println("opencall :"+empNo);
    }

    // 문 개폐
    @GetMapping("/open")
    public String open(String empNo) throws Exception{
        System.out.println("open :"+empNo);
        return  "openSuccess";
    }

}

