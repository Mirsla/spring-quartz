package com.alex.job.config;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author: Alex
 * @descripTion: 全局Job监听器
 * @date: Created in  20:01 2019/11/12
 * @modified By:
 */
public class GlobalJobListener implements JobListener {

    @Override
    public String getName() {
        String simpleName = getClass().getSimpleName();
        return simpleName;
    }

    /**
     * Job执行之前
     * @param context
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("jobToBeExecuted");
    }

    /**
     * Job被取消执行的时候
     * @param context
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("jobExecutionVetoed");
    }

    /**
     * Job执行之后
     * @param context
     * @param jobException
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("jobWasExecuted");
    }
}
