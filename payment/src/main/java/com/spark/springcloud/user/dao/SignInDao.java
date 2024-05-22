package com.spark.springcloud.user.dao;

import com.spark.springcloud.user.entities.SignIn;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignInDao {
    SignIn validateLogon(String userName, String passWord);
    Integer UpDataToken(Integer id, String token); // Mapper的upDate返回数据只能是Integer类型
}
