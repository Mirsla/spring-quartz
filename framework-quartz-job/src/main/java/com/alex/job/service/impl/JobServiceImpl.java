package com.alex.job.service.impl;

import com.alex.job.config.SchedulerUtil;
import com.alex.job.dao.JobDetailMapper;
import com.alex.job.entity.JobDetail;
import com.alex.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jobService")
@ConditionalOnExpression("#{'RAM'.equals(environment['qx.job.mode']) || 'DEFAULT_JX'.equals(environment['qx.job.mode'])}")
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDetailMapper jobDetailMapper;

    @Autowired
    private SchedulerUtil schedulerUtil;

    @Override
    public List<JobDetail> getJobDetailList() {
        return jobDetailMapper.selectJobDetails();
    }

    @Override
    public void addJob(String jobName, String jobClass, String jobGroup, String cronExpression, String description) {
        schedulerUtil.addJob(jobName, jobClass, jobGroup, cronExpression, description);
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
