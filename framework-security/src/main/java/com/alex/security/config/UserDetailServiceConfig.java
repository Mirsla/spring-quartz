package com.alex.security.config;

import com.alex.security.entity.SysUser;
import com.alex.security.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Alex
 * description: 自定义UserDetailService配置类，以实现自定义的登录认证
 * date: Created in  21:59 2019/11/14
 * modified By:
 */
@Configuration
public class UserDetailServiceConfig implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectByAccount(username);

        if(null == sysUser) {
            throw new UsernameNotFoundException(username);
        }
        // 这里手动添加一个管理员角色
        List<GrantedAuthority> roleList = new ArrayList<>();
        roleList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        roleList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(username, passwordEncoder.encode(sysUser.getPassword()), true, true, true, 0 == sysUser.getLocked(),
                roleList);
    }
}
