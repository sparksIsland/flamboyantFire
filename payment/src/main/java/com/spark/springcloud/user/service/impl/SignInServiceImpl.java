package com.spark.springcloud.user.service.impl;
import com.spark.springcloud.user.dao.SignInDao;
import com.spark.springcloud.user.entities.SignIn;
import com.spark.springcloud.user.service.SignInService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SignInServiceImpl implements SignInService {
    @Resource
    private SignInDao signInDao;
    @Override
    public SignIn validateLogon(String userName, String passWord) {
        return signInDao.validateLogon(userName, passWord);
    }
    @Override
    public Integer UpDataToken(Integer id, String token) {
        return signInDao.UpDataToken(id, token);
    }
}
