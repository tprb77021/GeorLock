package com.springboot.georlock.mapper;

import com.springboot.georlock.dto.Login;

import java.util.List;

public interface InsertMapper {
    public void insert(Login login) throws Exception;

    public List<Login> emplist() throws Exception;

    public List<Login> empSearch(String textSearch) throws Exception;

}
