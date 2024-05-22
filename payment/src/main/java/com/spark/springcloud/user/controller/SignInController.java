package com.spark.springcloud.user.controller;

import com.spark.springcloud.entities.CommonResult;
import com.spark.springcloud.user.entities.SignIn;
import com.spark.springcloud.user.service.SignInService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class SignInController {
    @Resource
    private SignInService signInService;
    @GetMapping(value = "/user")
    public String abc() {
        return "abc";
    }
    @GetMapping(value = "/user/sigin/{userName}/{passWord}")
    public CommonResult<SignIn> validateLogon(@PathVariable("userName") String userName, @PathVariable("passWord") String passWord) {
        SignIn signIn = signInService.validateLogon(userName, passWord);
        if (signIn != null) {
            return new CommonResult(200, "查询成功", signIn);
        } else {
            return new CommonResult(444, "用户名或密码错误", null);
        }
    }
}
