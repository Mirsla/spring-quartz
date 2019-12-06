package com.alex.security.config;

import com.alex.security.filter.SmsCodeAuthenticationFilter;
import com.alex.security.provider.SmsCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * description: SmsCode认证配置
 *      此处配置和BrowserSecurityConfig 差不不大，主要是配置过滤器，认证的提供者，还有认证成功后的处理器。
 * author: chenshoujiang
 * date: 2019/12/6
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private UserDetailsService userDetailServiceConfig;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationSuccessHandler(authSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(authFailureHandler);

        SmsCodeAuthenticationProvider smsAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(userDetailServiceConfig);

        http.authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
