package com.alex.job.dao;

import com.alex.job.entity.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {
    int deleteByPrimaryKey(Long companyId);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Long companyId);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    Company selectByCompanyCode(String companyCode);
}