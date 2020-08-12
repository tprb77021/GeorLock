package com.springboot.georlock.mapper;

import com.springboot.georlock.dto.Login;

import java.util.List;

public interface AccessMapper {
    public List<Login> getAll() throws Exception;

    public void update(Login login) throws Exception;

    public void delete(String empNo) throws Exception;

    public List<Login> Search(String textSearch) throws Exception;


}
