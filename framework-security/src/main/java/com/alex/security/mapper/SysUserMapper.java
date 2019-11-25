package com.alex.security.mapper;


import com.alex.security.entity.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名登录
     * @param account   包含 手机号，邮箱
     * @return 系统用户
     */
    SysUser selectByAccount(String account);
}