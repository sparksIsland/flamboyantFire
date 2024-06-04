package com.user.service;


import com.user.entities.SignIn;

/**
 * @author liupeng
 */
public interface SignInService {
    SignIn validateLogon(String userName, String passWord);

    Integer UpDataToken(Integer id, String token);
}
