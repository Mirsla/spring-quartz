package com.alex.job.config;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author: Alex
 * @descripTion:
 * @date: Created in  21:16 2019/11/12
 * @modified By:
 */
public class Job implements org.quartz.Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        BaseJob bean = SpringBeanUtil.getBean(jobDataMap.get("beanName").toString(), BaseJob.class);
        System.out.println("------------------------");
        System.out.println(bean);
        bean.execute(context);
        System.out.println("------------------------");
        bean.execute(context);
    }
}
