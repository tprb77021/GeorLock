package com.springboot.georlock.mapper;

import com.springboot.georlock.dto.Login;
import org.json.JSONException;


public interface LoginMapper {
    public  String getToken(String empNo) ;

    public Login Login(Login login) throws Exception;

    public void userUpdate(Login login) throws Exception;

    void updateToken(Login login) throws Exception;
}
