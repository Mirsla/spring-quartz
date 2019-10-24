package com.alex.job.service;

import com.alex.job.entity.JobDetail;

import java.util.List;

public interface JobService {

    /**
     * 获取job列表
     * @return  获取job列表
     */
    List<JobDetail> getJobDetailList();

    /**
     * 添加job
     * @param jobName job名称
     * @param jobClass 需要运行的job class 类名， 执行业务的主题
     * @param jobGroup  job所在分组名称
     * @param cronExpression cron表达式
     * @param description   job注解及描述
     */
    void addJob(String jobName, String jobClass, String jobGroup, String cronExpression, String description);

    /**
     * 删除job     由job名称和job所在组确定一个job
     * @param jobName job名称
     * @param jobGroup job所在的组
     */
    void deleteJob(String jobName, String jobGroup);

    /**
     * 修改job的执行方式（即修改job的cron表达式）
     * @param jobName   job名称
     * @param jobGroup  job所在分组
     * @param cronExpression    job的cron表达式
     */
    void rescheduleJob(String jobName, String jobGroup, String cronExpression);

    /**
     * 暂停job    由job名称和job所在组确定一个job
     * @param jobName job名称
     * @param jobGroup job所在的组
     */
    void pauseJob(String jobName, String jobGroup);

    /**
     * 恢复job    由job名称和job所在组确定一个job
     * @param jobName jobName job名称
     * @param jobGroup jobGroup job所在的组
     */
    void resumeJob(String jobName, String jobGroup);

    /**
     * 立即执行一次job
     * @param jobName   jobName job名称
     * @param jobGroup 任务所在组
     */
    void triggerJob(String jobName, String jobGroup);
}
