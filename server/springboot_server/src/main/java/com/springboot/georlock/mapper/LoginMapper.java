package com.springboot.georlock.mapper;

import com.springboot.georlock.dto.Login;


public interface LoginMapper {
    public Login Login(Login login) throws Exception;

    public void userUpdate(Login login) throws Exception;
}
