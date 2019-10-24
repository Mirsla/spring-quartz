package com.alex.job.dao;


import com.alex.job.entity.JobDetail;

import java.util.List;

public interface JobDetailMapper {

    List<JobDetail> selectJobDetails();
}
