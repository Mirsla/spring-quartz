package com.alex.job.dao;

import com.alex.job.entity.JobDetail;
import com.alex.job.entity.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleJobMapper {
    int deleteByPrimaryKey(Integer jobId);

    int insert(ScheduleJob record);

    int insertSelective(ScheduleJob record);

    ScheduleJob selectByPrimaryKey(Integer jobId);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);

    List<JobDetail> selectJobDetails();
}