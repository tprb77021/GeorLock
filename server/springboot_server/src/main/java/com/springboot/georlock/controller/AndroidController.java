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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class AndroidController {
    final String ADMIN_EMPNO = "11110000";
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


    @GetMapping("/q")     //테스트용
    public List<Login> query() throws Exception {
        return testService.getAll();
    }


    @GetMapping("/openlist")    //출입 기록 리스트
    public List<Enteremp> openlist() throws Exception {
        return recordService.getEnterEmp(); // 안드로이드에 출입 기록 전송
    }

    @GetMapping("/openSearch") //검색된 출입 기록
    public List<Enteremp> openSearch(String search, String startDate, String endDate) throws Exception {
        Dates dates = new Dates();
        dates.setStartDate(startDate);
        dates.setEndDate(endDate);
        dates.setTextSearch(search);
        return recordService.getRecordSearch(dates); // 안드로이드에 검색된 출입 기록 전송
    }


    @GetMapping("/accesslist")  //출입 권한 리스트
    public List<Login> accesslist() throws Exception {
        return testService.getAll();  // 안드로이드에 나타낼 검색된 회원 정보(출입 권한)
    }

    @GetMapping("/accessSearch")  //검색된 출입 권한
    public List<Login> accessSearch(String search) throws Exception {
        return accessService.accessSearch(search);  // 안드로이드에 나타낼 검색된 회원 정보(출입 권한)
    }

    @GetMapping("/delete")      // 출입 권한 삭제
    public void delete(String empNo) throws Exception {
        accessService.accessDelete(empNo); //출입 권한 삭제 실행
    }

    @GetMapping("/update")      // 출입 권한 수정
    public void update(String empNo, String intime, String outtime) throws Exception {
        Login login = new Login();
        login.setEmpNo(empNo);
        login.setIntime(intime);
        login.setOuttime(outtime);
        accessService.accessUpdate(login);  //출입 권한 수정 실행
    }


    @GetMapping("/login")   //로그인
    public String login(String empNo, String userPw, String tokens) throws Exception {
        Login login = loginService.Login(empNo, userPw);     //로그인시도
        String log = "0실패";   //0실패 : 로그인실패 ,1성공 : 일반사용자 ,2성공 : 관리자
        if (login.getUsertype().equals("1")) {
            log = "1성공@" + login.getIntime() + "@" + login.getOuttime() + "@" + login.getEmpNo();
            loginService.updateToken(tokens, empNo);
        } else if (login.getUsertype().equals("2")) {
            log = "2성공@" + login.getIntime() + "@" + login.getOuttime() + "@" + login.getEmpNo();
            loginService.updateToken(tokens, empNo);
        }

        return log;      //안드로이드에 로그인 결과 전송
    }


    @GetMapping("/userupdate")  //개인정보 수정
    public void userupdate(String empNo, String userPw) throws Exception {
        loginService.userUpdate(empNo, userPw);  //개인정보 수정 실행
    }

    @GetMapping("/getDoor")  //문 상태 확인
    public String getDoor() throws Exception {
        return loginService.getDoor();  //현재 문상태 전송
    }


    @GetMapping("/opencall")    //출입 개폐 요청(firebase 사용)
    public @ResponseBody
    ResponseEntity<String> opencall(String empNo) throws JSONException, InterruptedException {
        Login token = loginService.getToken(ADMIN_EMPNO);  //토큰값 조회

        String notifications =
                AndroidPushPeriodicNotifications.PeriodicNotificationJson(token, 1, "");
        // 푸시메시지 전송을 위한 토큰값과 타입 설정

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


    @GetMapping("/open")     //출입문 열기 및 안드로이드 상태에 문상태 푸시로 전송
    public @ResponseBody
    void open(String empNo) throws JSONException, InterruptedException {
        loginService.setdoor(1, empNo);
        send();
    }

    @GetMapping("/close")    //출입문 닫기 및 안드로이드 상태에 문상태 푸시로 전송
    public @ResponseBody
    void close() throws JSONException, InterruptedException {
        loginService.setdoor(0);
        send();
    }

    // 안드로이드 상태에 문상태 푸시로 전송
    public ResponseEntity<String> send() throws JSONException, InterruptedException {
        Login token = loginService.getToken(ADMIN_EMPNO);
        String notifications =
                AndroidPushPeriodicNotifications.PeriodicNotificationJson(token, 2, loginService.getDoor());
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



