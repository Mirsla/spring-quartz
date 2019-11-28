package com.alex.security.mapper;


import com.alex.security.entity.RoleAuthority;

import java.util.List;
import java.util.Map;

public interface RoleAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleAuthority record);

    int insertSelective(RoleAuthority record);

    RoleAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleAuthority record);

    int updateByPrimaryKey(RoleAuthority record);

    List<Map<String, String>> selectAllAuthorityRelation();
}