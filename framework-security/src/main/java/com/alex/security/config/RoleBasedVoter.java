package com.alex.security.config;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 自定义权限投票器
 *      在config中不再配置哪个页面需要什么权限。原有的权限投票器还是继续使用，
 *      此投票器用于自定义权限的控制数据来源可以是数据库（目前为了方便先写死在这里）
 * author: chenshoujiang
 * date: 2019/11/27
 */
public class RoleBasedVoter implements AccessDecisionVoter<FilterInvocation> {

    private String rolePrefix = "ROLE_";

    public String getRolePrefix() {
        return rolePrefix;
    }

    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }

    public boolean supports(ConfigAttribute attribute) {
        if ((attribute.getAttribute() != null)
                && attribute.getAttribute().startsWith(getRolePrefix())) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    public int vote(Authentication authentication, FilterInvocation object,
                    Collection<ConfigAttribute> attributes) {

        // 如果没有指定认证方式直接返回-1；
        if(authentication == null) {
            return ACCESS_DENIED;
        }
        // 默认的结果是0，不返回任何认证方式
        int result = ACCESS_ABSTAIN;
        // 获取当前用户所有的权限。
        Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);

        // 获取当前访问的url
        String url = object.getRequestUrl();

        // 模拟的数据
        Map<String,String> authorityMap = new HashMap<>();
//        authorityMap.put("/index","ROLE_ANONYMOUS"); //主页可以随意访问
//        authorityMap.put("/user","ROLE_ADMIN");
//        authorityMap.put("/log","ROLE_ADMIN");
//        authorityMap.put("/role","ROLE_ADMIN");
//        authorityMap.put("/product","ROLE_USER");
//        authorityMap.put("/menu","ROLE_USER2");

        String o = authorityMap.get(url);
        if(!StringUtils.isEmpty(o)) {
            boolean flag = true;
            for (GrantedAuthority authority : authorities) {
                if(o.equals(authority.getAuthority())) {
                    flag = false;
                    result =  ACCESS_GRANTED;
                    break;
                }
            }
            if(flag)
                result = ACCESS_DENIED;
        }

        return result;
    }

    Collection<? extends GrantedAuthority> extractAuthorities(
            Authentication authentication) {
        return authentication.getAuthorities();
    }
}
