package com.alex.security.entity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * description: 用于描述指定的uri可以访问的角色和请求方法类型
 * author: chenshoujiang
 * date: 2019/11/28
 */
public class UriAuthority {

    private Map<String, Set<String>> sdfas;

    private Set<String> roles;

    private Set<String> methods;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getMethods() {
        return methods;
    }

    public void setMethods(Set<String> methods) {
        this.methods = methods;
    }

    public UriAuthority() {
    }

    public UriAuthority(String role, String method) {
        roles = new HashSet<>(2);
        this.roles.add(role);
        methods = new HashSet<>(2);
        this.methods.add(method);
    }
}
