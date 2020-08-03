package com.springboot.georlock.controller;

import com.springboot.georlock.dto.Dates;
import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.dto.Login;
import com.springboot.georlock.svc.*;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    @Autowired
    AndroidPushNotificationService androidPushNotificationService;

    Logger logger = LoggerFactory.getLogger(this.getClass());
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
        dates.setEndDate(endDate);
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
    public String login(String empNo, String userPw,String tokens) throws Exception{
        Login login=loginService.Login(empNo,userPw);

        String log="0실패";
        if(login.getUsertype().equals("1")){
            log="1성공@"+login.getIntime()+"@"+login.getOuttime()+"@"+login.getEmpNo();
            loginService.updateToken(tokens,empNo);
        }
        else if(login.getUsertype().equals("2")){
            log="2성공@"+login.getIntime()+"@"+login.getOuttime()+"@"+login.getEmpNo();
            loginService.updateToken(tokens,empNo);
        }
        return log;
    }

    //개인정보 수정
    @GetMapping("/userupdate")
    public void userupdate(String empNo,String userPw) throws Exception{
        loginService.userUpdate(empNo,userPw);
    }

    @GetMapping(value ="/door")
    public String Door() throws Exception{
        return loginService.getdoor();
    }


    //출입 요청


    // 문 개폐
  /*  @GetMapping("/open")
    public String open(String empNo) throws Exception{

        return  "openSuccess";
    }*/





    //    @Scheduled(fixedRate = 10000)
    @GetMapping("/opencall")
    public @ResponseBody
    ResponseEntity<String> opencall(String empNo) throws JSONException, InterruptedException {
        Login token=loginService.getToken("11110000");
        String notifications =
                AndroidPushPeriodicNotifications.PeriodicNotificationJson(token,1,"");
        HttpEntity<String> request = new HttpEntity<>(notifications);
        CompletableFuture<String> pushNotification =
                androidPushNotificationService.send(request);
        CompletableFuture.allOf(pushNotification).join();
        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException e) {
            logger.debug("got interrupted!");
            throw new InterruptedException();
        } catch (ExecutionException e) {
            logger.debug("execution error!");
        }
        return new ResponseEntity<>("Push Notification ERROR!",
                HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/open")
    public @ResponseBody
    ResponseEntity<String> open(String empNo) throws JSONException, InterruptedException {
        Login token=loginService.getToken("11110000");
        loginService.setdoor(1);
        String notifications =
                AndroidPushPeriodicNotifications.PeriodicNotificationJson(token,2,loginService.getdoor());
        HttpEntity<String> request = new HttpEntity<>(notifications);
        CompletableFuture<String> pushNotification =
                androidPushNotificationService.send(request);
        CompletableFuture.allOf(pushNotification).join();
        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException e) {
            logger.debug("got interrupted!");
            throw new InterruptedException();
        } catch (ExecutionException e) {
            logger.debug("execution error!");
        }
        return new ResponseEntity<>("Push Notification ERROR!",
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/close")
    public @ResponseBody
    ResponseEntity<String> close() throws JSONException, InterruptedException {
        Login token=loginService.getToken("11110000");
        loginService.setdoor(0);
        String notifications =
                AndroidPushPeriodicNotifications.PeriodicNotificationJson(token,2,loginService.getdoor());
        HttpEntity<String> request = new HttpEntity<>(notifications);
        CompletableFuture<String> pushNotification =
                androidPushNotificationService.send(request);
        CompletableFuture.allOf(pushNotification).join();
        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException e) {
            logger.debug("got interrupted!");
            throw new InterruptedException();
        } catch (ExecutionException e) {
            logger.debug("execution error!");
        }
        return new ResponseEntity<>("Push Notification ERROR!",
                HttpStatus.BAD_REQUEST);
    }



}



