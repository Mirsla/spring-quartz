package com.alex.security.config;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 权限安全过滤器，是实现自定义权限管理的另外一种方式
 *
 * author: chenshoujiang
 * date: 2019/11/27
 */
public class AppFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private FilterInvocationSecurityMetadataSource  superMetadataSource;

    public AppFilterInvocationSecurityMetadataSource(FilterInvocationSecurityMetadataSource superMetadataSource) {
        this.superMetadataSource = superMetadataSource;

        // todo 从这里加载数据库权限

    }

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    // TODO 这部分数据应该是从数据库获取的
    private final Map<String,String> urlRoleMap = new HashMap<String,String>(){{
        put("/user","ROLE_ADMIN");
        put("/log","ROLE_ADMIN");
        put("/role","ROLE_ADMIN");
        put("/product","ROLE_USER");
        put("/menu","ROLE_USER2");
    }};

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();

        for(Map.Entry<String,String> entry:urlRoleMap.entrySet()){
            if(antPathMatcher.match(entry.getKey(),url)){
                return SecurityConfig.createList(entry.getValue());
            }
        }

        //  返回代码定义的默认配置
        return superMetadataSource.getAttributes(object);
    }

    @Override
        public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
