package com.alex.security.config;

import com.alex.security.entity.UriAuthority;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.*;

/**
 * description: 权限安全过滤器，是实现自定义权限管理的另外一种方式
 *
 * author: chenshoujiang
 * date: 2019/11/27
 */
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private FilterInvocationSecurityMetadataSource  superMetadataSource;

    private final Map<String, Map<String, Set<String>>> requestMap = new HashMap<>();

    public CustomSecurityMetadataSource(FilterInvocationSecurityMetadataSource superMetadataSource, Map<String, Map<String, Set<String>>> requestMap) {
        this.superMetadataSource = superMetadataSource;
        this.requestMap.putAll(requestMap);
    }

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        String method = fi.getRequest().getMethod();

        for(Map.Entry<String,Map<String, Set<String>>> entry: requestMap.entrySet()){
            if(antPathMatcher.match(entry.getKey(), url)){
                // todo 暂时不做method的判断
                Map<String, Set<String>> value = entry.getValue();
                Set<String> roles = value.keySet();
                List<ConfigAttribute> attributes = new ArrayList<>(
                        roles.size());

                for (String attribute : roles) {
                    attributes.add(new SecurityConfig("ROLE_" + attribute.trim().toUpperCase()));
                }

                return attributes;
            }
        }

        //  返回代码定义的默认配置
        return superMetadataSource.getAttributes(object);
    }

    /**
     * getAllConfigAttributes方法如果返回了所有定义的权限资源，Spring Security会在启动时校验每个ConfigAttribute是否配置正确，不需要校验直接返回null
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    // 加载权限元数据
    public void reloadSecurityMetadataSource(Map<String, Object> requestMap) {

    }
}
