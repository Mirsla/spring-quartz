package com.alex.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description: Spring 安全框架学习启动类
 * author: chenshoujiang
 * date: 2019/11/11
 */
@SpringBootApplication
@MapperScan({"com.alex.security.mapper"})
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class,args);
    }
}
