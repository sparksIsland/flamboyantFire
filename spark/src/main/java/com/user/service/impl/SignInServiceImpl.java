package com.user.service.impl;

import com.user.dao.SignInMapper;
import com.user.entities.SignIn;
import com.user.service.SignInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SignInServiceImpl implements SignInService {
    @Resource
    private SignInMapper signInMapper;

    @Override
    public SignIn validateLogon(String userName, String passWord) {
        log.info(userName + passWord);
        return signInMapper.validateLogon(userName, passWord);
    }

    @Override
    public Integer UpDataToken(Integer id, String token) {
        return signInMapper.UpDataToken(id, token);
    }
}
