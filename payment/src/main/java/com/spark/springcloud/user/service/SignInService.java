package com.spark.springcloud.user.service;

import com.spark.springcloud.user.entities.SignIn;

public interface SignInService {
    SignIn validateLogon(String userName, String passWord);
    Integer UpDataToken(Integer id, String token);
}
