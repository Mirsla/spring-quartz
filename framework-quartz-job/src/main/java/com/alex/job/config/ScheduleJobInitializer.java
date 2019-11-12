package com.alex.job.config;

import com.alex.job.dao.ScheduleJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 用于在容器启动的时候初始化所有的job
 */
@Configuration
public class ScheduleJobInitializer {
    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @PostConstruct
    public void initJob() {
//        scheduleJobMapper.get.....
        //然后依次对Job进行初始化操作
//        System.out.println(scheduleJobMapper);
//        System.out.println(11111);
    }
}
