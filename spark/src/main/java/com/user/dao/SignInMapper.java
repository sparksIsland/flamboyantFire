package com.user.dao;

import com.user.entities.SignIn;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignInMapper {
    /**
     * 验证账号密码
     */
    SignIn validateLogon(String userName, String passWord);

    /**
     * 更新 token
     */
    Integer UpDataToken(Integer id, String token);
}
