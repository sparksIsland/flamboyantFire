package com.user.service;


import com.user.entities.SignIn;

public interface SignInService {
    SignIn validateLogon(String userName, String passWord);

    Integer UpDataToken(Integer id, String token);
}
