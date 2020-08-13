package com.springboot.georlock.controller;

import com.springboot.georlock.svc.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RpiController {
    @Autowired
    LoginService loginService;

    @GetMapping("/doorOpen")  //출입문 개폐 시도
    public String doorOpen(String NFCdata) throws Exception {
        String log = loginService.doorOpenTry(NFCdata); //nfc 번호로 출입 가능 여부 확인
        return log; // 출입가능 : 1 , 불가능 : 0
    }

    @GetMapping("/getNfc") //nfc 등록 (nfc쓰기모드)
    public String getNfc() throws Exception {
        String log = loginService.getInsertNfc(); //라즈베리파이로 nfc값 전송
        return log;
    }

    @GetMapping("/resetNfc")  //nfc 등록 완료시 실행
    public void resetNfc() throws Exception {
        loginService.setNfc("0");   //nfc 값 초기화
    }
}
