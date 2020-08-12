package com.springboot.georlock.mapper;

import com.springboot.georlock.dto.Door;
import com.springboot.georlock.dto.Login;
import org.json.JSONException;


public interface LoginMapper {
    public Login getToken(String empNo);

    public Login Login(Login login) throws Exception;

    public void userUpdate(Login login) throws Exception;

    void updateToken(Login login) throws Exception;

    Door getdoor();

    void setdoor(int i);

    public Login doorOpenTry(String cardValue);

    void enteremp(Login login);

    Login selectuser(String empNo);

    void setnfc(String nfc);
}
