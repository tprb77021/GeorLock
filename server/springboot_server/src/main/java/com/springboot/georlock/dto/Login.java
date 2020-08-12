package com.springboot.georlock.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Login {
    private String empNo;
    private String userPw;
    private String username;
    private String intime = "00:00";
    private String outtime = "00:00";
    private String usertype = "1";
    private String token;
    private String nfc;
}
