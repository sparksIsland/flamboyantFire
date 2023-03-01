package com.spark.springcloud.user.dao;

import com.spark.springcloud.user.entities.SignIn;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignInDao {
    SignIn validateLogon(String userName, String passWord);
}
