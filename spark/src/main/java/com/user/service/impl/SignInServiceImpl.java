package com.user.service.impl;

import com.user.dao.SignInDao;
import com.user.entities.SignIn;
import com.user.service.SignInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SignInServiceImpl implements SignInService {
    @Resource
    private SignInDao signInDao;

    @Override
    public SignIn validateLogon(String userName, String passWord) {
        log.info(userName + passWord);
        return signInDao.validateLogon(userName, passWord);
    }

    @Override
    public Integer UpDataToken(Integer id, String token) {
        return signInDao.UpDataToken(id, token);
    }
}
