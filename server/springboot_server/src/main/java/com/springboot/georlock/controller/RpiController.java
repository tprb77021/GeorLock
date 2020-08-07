package com.springboot.georlock.controller;

import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.svc.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RpiController {
    @Autowired
    LoginService loginService;

    @GetMapping( "/dooropen" )  //출입문 개폐 시도
    public String openlist(String cardValue) throws Exception{
        String log=loginService.doorOpenTry(cardValue); //nfc 번호로 출입 가능 여부 확인
        return log; // 출입가능 : 1 , 불가능 : 0
    }
    @GetMapping( "/insertnfc" ) //nfc 등록 (nfc쓰기모드)
    public String insertnfc() throws Exception{
        String log=loginService.getinsertnfc(); //라즈베리파이로 nfc값 전송
        return log;
    }

    @GetMapping( "/resetnfc" )  //nfc 등록 완료시 실행
    public void resetnfc() throws Exception{
        loginService.setnfc("0");   //nfc 값 초기화
    }





}
