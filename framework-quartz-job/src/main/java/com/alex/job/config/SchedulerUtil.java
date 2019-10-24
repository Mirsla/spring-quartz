package com.alex.job.config;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SchedulerUtil {

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    public void addJob(String jobName, String jobClass, String jobGroup, String cronExpression, String description) {
        try {
            // 启动调度器
            scheduler.start();

            //构建job信息
            org.quartz.JobDetail jobDetail = JobBuilder.newJob(getClass(jobClass).getClass()).withIdentity(jobName, jobGroup).withDescription(description).build();

            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
                    .withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            // job创建失败
        } catch (ClassNotFoundException e) {
            // 业务job类未找到
        } catch (Exception e) {
            // 其他异常
        }
    }

    public void deleteJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            // job 删除失败
        }
    }


    public void rescheduleJob(String jobName, String jobGroup, String cronExpression) {
        try {
            // csjtodo 触发器的名称是如何找的。在创建的时候是如何设置的。这个问题先搞清楚
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败"+e);
        }
    }

    public void pauseJob(String jobName, String jobGroup) {
        try{
            scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
        } catch (SchedulerException e) {

        }
    }

    public void resumeJob(String jobName, String jobGroup) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
        } catch (SchedulerException e) {

        }
    }

    private static BaseJob getClass(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        return (BaseJob) clazz.newInstance();

    }

    public void triggerJob(String jobName, String jobGroup) {
        try {
            scheduler.triggerJob(JobKey.jobKey(jobName,jobGroup));
        } catch (SchedulerException e) {

        }
    }
}
