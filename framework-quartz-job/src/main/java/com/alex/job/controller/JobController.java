package com.alex.job.controller;


import com.alex.job.extend.BaseController;
import com.alex.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("job")
@Controller
public class JobController extends BaseController {

    @Autowired
    private JobService jobService;

    /**
     * job主页
     * @return string job_index job控制主页
     */
    @RequestMapping("index")
    public String jobIndex() {
        return "job_index";
    }

    /**
     * job list 页面
     * @return job_list  页面
     */
    @RequestMapping("list_page")
    public String jobList() {
        return "job_list";
    }

    /**
     * 返回job列表
     * @return 任务列表
     */
    @RequestMapping("list")
    @ResponseBody
    public Object list() {
        return jobService.getJobDetailList();
    }

    /**
     * 添加job
     * @param jobName job名称
     * @param jobClass  job业务的class完整名称
     * @param jobGroup jobGroup 任务组
     * @param cronExpression    cron表达式
     * @param description   job的描述相关
     * @return Object
     */
    @ResponseBody
    @RequestMapping("add")
    public Object addJob(String jobName, String jobClass, String jobGroup, String cronExpression, String description) {
        jobService.addJob(jobName, jobClass, jobGroup, cronExpression, description);
        return wrapResult();
    }

    /**
     * 删除job
     * @param jobName   job名称
     * @param jobGroup  任务组
     * @return Object
     */
    @ResponseBody
    @RequestMapping("delete")
    public Object deleteJob(String jobName, String jobGroup) {
        jobService.deleteJob(jobName, jobGroup);
        return wrapResult();
    }

    /**
     * 修改job的cron表达式
     * @param jobName   job名称
     * @param jobGroup 任务组
     * @param cronExpression    cron表达式
     * @return Object
     */
    @ResponseBody
    @RequestMapping("reschedule")
    public Object rescheduleJob(String jobName, String jobGroup, String cronExpression) {
        jobService.rescheduleJob(jobName, jobGroup, cronExpression);
        return wrapResult();
    }

    /**
     * 暂停job
     * @param jobName   job名称
     * @param jobGroup 任务组
     * @return Object
     */
    @ResponseBody
    @RequestMapping("pause")
    public Object pauseJob(String jobName, String jobGroup) {
        jobService.pauseJob(jobName, jobGroup);
        return wrapResult();
    }

    /**
     * 恢复job
     * @param jobName   job名称
     * @param jobGroup 任务组
     * @return Object
     */
    @ResponseBody
    @RequestMapping("resume")
    public Object resumeJob(String jobName, String jobGroup) {
        jobService.resumeJob(jobName, jobGroup);
        return wrapResult();
    }

    /**
     * 立即执行一次job
     * @param jobName job名称
     * @param jobGroup 任务组
     * @return Object
     */
    @ResponseBody
    @RequestMapping("trigger")
    public Object triggerJob(String jobName, String jobGroup) {
        jobService.triggerJob(jobName, jobGroup);
        return wrapResult();
    }
}
