package com.spark.springcloud.user.controller;

import com.spark.springcloud.entities.CommonResult;
import com.spark.springcloud.user.entities.SignIn;
import com.spark.springcloud.user.service.SignInService;
import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

@RestController
@Slf4j
public class SignInController {
    @Resource
    private SignInService signInService;
    @GetMapping(value = "/spark/user")
    public String abc() {
        return "abc";
    }
    @GetMapping(value = "/spark/user/sigin/{userName}/{passWord}")
    public CommonResult<SignIn> validateLogon(@PathVariable("userName") String userName, @PathVariable("passWord") String passWord) {
        SignIn signIn = signInService.validateLogon(userName, passWord);
//        if (signIn.getToken() != "") {
//            Long time = Long.valueOf(signIn.getTokenTime().getTime());
//            Long timeNow = Long.valueOf(new Date().getTime());
//            if ((timeNow - time) > 1000*60*5) {
//                log.info(String.valueOf(timeNow - time));
//                return new CommonResult(450, "Token已过期", signIn);
//            } else {
//                log.info(String.valueOf(timeNow - time));
//            }
//            log.info(time > timeNow);
//            log.info(timeNow);
//        }
        if (signIn != null) {
            String token = UUID.randomUUID().toString().replaceAll("-", "") + "";
            log.info(token);
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("md5");
                byte md5[] = md.digest(token.getBytes());
                //base64编码--任意二进制编码明文字符 adfsdfsdfsf
                BASE64Encoder encoder = new BASE64Encoder();
                token = encoder.encode(md5);
                log.info(token);
                Integer isOK = signInService.UpDataToken(signIn.getId(), token);
                log.info(String.valueOf(isOK));
                if(isOK == 1) {
                    return new CommonResult(200, "查询成功", token);
                } else {
                    return new CommonResult(500, "发生未知错误", null);
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
//            Integer isOK = signInService.UpDataToken(signIn.getId(), token);
//            log.info(String.valueOf(isOK));
//            if(isOK == 1) {
//                return new CommonResult(200, "查询成功", token);
//            } else {
//                return new CommonResult(500, "发生未知错误", null);
//            }
        } else {
            return new CommonResult(444, "用户名或密码错误", null);
        }
    }
}
