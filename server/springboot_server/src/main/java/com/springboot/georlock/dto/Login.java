package com.springboot.georlock.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Login {
       private  String empNo ;
       private String userPw ;
       private String username;
       private String intime ;
       private String outtime ;
}
