package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassNmae PaymentMain：
 * TODO：启动类
 * @Author spark、2022/9/21、6:45：
 * 1.0
 **/
@SpringBootApplication
@EnableTransactionManagement
public class SparkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparkApplication.class, args);
    }
}
