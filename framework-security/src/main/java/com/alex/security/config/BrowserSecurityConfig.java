package com.alex.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfigurerAdapter是由Spring Security提供的Web应用安全配置的适配器
 */

@Configuration
@EnableWebSecurity  //启动Spring Security的过滤器链
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // 授权配置拦截
                .antMatchers("/css/**","/js/**","/layui/**","/layuiadmin/**","/fonts/**", "/favicon.ico").permitAll()   //配置的。css下的，js下的 等所有资源所有用户都能访问
                .anyRequest().authenticated() //其他没有配置的URL，对用户只需要进行身份验证
                .and()
                .formLogin()    //表单方式登录
                .loginPage("/login")    //配置登录页面为login
                .loginProcessingUrl("/toLogin") //登录页面from表单的登录请求处理接口
                .successHandler(authSuccessHandler) //登录成功处理器
                .permitAll()  // loginPage().permitAll() 方法允许向所有用户授予与基于表单的登录相关联的所有URL的访问权限
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
