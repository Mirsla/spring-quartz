package com.alex.job;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({"com.qx.lottery.job.service","com.qx.lottery.controller","com.qx.demo"})
//@ComponentScan("com.alex.job")
@MapperScan({"com.alex.job.dao"})
public class QuartzJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzJobApplication.class, args);
    }
}
