package com.alex.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.Arrays;
import java.util.List;

/**
 * WebSecurityConfigurerAdapter是由Spring Security提供的Web应用安全配置的适配器
 */

@Configuration
@EnableWebSecurity  //启动Spring Security的过滤器链
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private AuthFailureHandler authFailureHandler;

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // 授权配置拦截
                .antMatchers("/css/**","/js/**","/layui/**","/layuiadmin/**","/fonts/**", "/favicon.ico").permitAll()   //配置的。css下的，js下的 等所有资源所有用户都能访问
            .and()
                .authorizeRequests()
//                .antMatchers("/user","/log","/role").hasRole("ADMIN") //这里配置的三个页面需要 ROLE_ADMIN的权限才能访问
//                .antMatchers("/product","/menu").hasRole("USER")  // 这里配置的两个页面需要ROLE_USER权限才能访问
                .anyRequest().authenticated() //其他没有配置的URL，对用户只需要进行身份验证
                .accessDecisionManager(accessDecisionManager()) //配置访问决策管理器
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(mySecurityMetadataSource(object.getSecurityMetadataSource()));
                        return object;
                    }
                }) // 自定义FilterInvocationSecurityMetadataSource
            .and()
                .formLogin()    //表单方式登录
                .loginPage("/login")    //配置登录页面为login
                .loginProcessingUrl("/login") //登录页面from表单的登录请求处理接口, 如果没有配置的情况下，默认使用与loginPage相同的配置
//                .failureUrl("/login?error=true")    // 这采用的是登录错误跳转的链接。
                .failureHandler(authFailureHandler) //登录失败的处理器
                .successHandler(authSuccessHandler) //登录成功处理器
                .defaultSuccessUrl("/index")    //默认成功跳转页面，如果配置了多个成功后的处理，只有最后一个生效
                .permitAll()  // loginPage().permitAll() 方法允许向所有用户授予与基于表单的登录相关联的所有URL的访问权限
            .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler()) //权限不足处理器
            .and()
                .csrf()
                .disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义异常页
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new SecurityAccessDeniedHandler();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new WebExpressionVoter(),
                new RoleVoter(),
//                new RoleBasedVoter(),
                new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }

    @Bean
    public AppFilterInvocationSecurityMetadataSource mySecurityMetadataSource(FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource) {
        return new AppFilterInvocationSecurityMetadataSource(filterInvocationSecurityMetadataSource);
    }
}
