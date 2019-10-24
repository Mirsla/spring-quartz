package com.alex.job.service.impl;

import com.alex.job.config.SchedulerUtil;
import com.alex.job.dao.ScheduleJobMapper;
import com.alex.job.entity.JobDetail;
import com.alex.job.entity.ScheduleJob;
import com.alex.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * csjtodo 关于自定义持久化相关的逻辑暂时未完成
 *  无非就是修改数据库中对应job的状态或者是添加job，先放这里了
 */
@Service
@ConditionalOnProperty(prefix = "qx.job", name = "mode", havingValue = "CUSTOM_JX")
public class CustomJobServiceImpl implements JobService {

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Autowired
    private SchedulerUtil schedulerUtil;

    @Override
    public List<JobDetail> getJobDetailList() {
        return scheduleJobMapper.selectJobDetails();
    }

    @Override
    public void addJob(String jobName, String jobClass, String jobGroup, String cronExpression, String description) {
        schedulerUtil.addJob(jobName, jobClass, jobGroup, cronExpression, description);

        // 将数据持久化到数据库中
        ScheduleJob scheduleJob = new ScheduleJob();
    }

    @Override
    public void deleteJob(String jobName, String jobGroup) {
        schedulerUtil.deleteJob(jobName, jobGroup);
    }


    @Override
    public void rescheduleJob(String jobName, String jobGroup, String cronExpression) {
        schedulerUtil.rescheduleJob(jobName, jobGroup, cronExpression);
    }

    @Override
    public void pauseJob(String jobName, String jobGroup) {
        schedulerUtil.pauseJob(jobName, jobGroup);
    }

    @Override
    public void resumeJob(String jobName, String jobGroup) {
        schedulerUtil.resumeJob(jobName, jobGroup);
    }

    @Override
    public void triggerJob(String jobName, String jobGroup) {
        schedulerUtil.triggerJob(jobName, jobGroup);
    }
}
