package com.alex.job.detail.lottery;

import com.alex.job.config.BaseJob;
import com.alex.job.config.SpringBeanUtil;
import com.alex.job.service.CompanyService;
import com.alex.job.service.JobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyJob implements BaseJob {

    @Autowired
    private JobService jobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if(null == SpringBeanUtil.getApplicationContext()) {
            System.out.println("ahahhahahahahah");
        } else {
            System.out.println(SpringBeanUtil.getApplicationContext());
        }
        SpringBeanUtil.getBean(CompanyService.class).saveCompany();
    }
}
