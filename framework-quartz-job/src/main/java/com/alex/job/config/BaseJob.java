package com.alex.job.config;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public interface BaseJob extends Job {

    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;

}
