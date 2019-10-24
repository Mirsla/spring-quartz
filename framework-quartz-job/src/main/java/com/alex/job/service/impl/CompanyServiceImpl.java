package com.alex.job.service.impl;

import com.alex.job.dao.CompanyMapper;
import com.alex.job.entity.Company;
import com.alex.job.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public void saveCompany() {
        Company company = new Company();
        company.setCompanyName("ziyang");
        company.setCompanyCode("zy");
        company.setProvince("四川");
        company.setCity("成都市");
        company.setRegion("高新区");

        company.setCompanyCategory(new Byte("1"));
        company.setPackageVersion("1");
        company.setProjectCount(1);
        company.setStatus(new Byte("1"));
        company.setClientCustomMade("111");
        company.setWeChatOfficialAccounts("111");
        company.setCreatedTime(new Date().getTime());
        company.setCreatedBy("ziyang");
        company.setUpdateTime(0L);
        company.setUpdateBy("");
        companyMapper.insert(company);

        try {
            Thread.sleep(10000);
        } catch (Exception e) {

        }
        company.setUpdateBy("ziyang");
        company.setUpdateTime(new Date().getTime());
        companyMapper.updateByPrimaryKey(company);
    }
}
