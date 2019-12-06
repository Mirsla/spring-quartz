package com.alex.security.filter;

import com.alex.security.config.AuthFailureHandler;
import com.alex.security.entity.SmsCode;
import com.alex.security.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: 和ValidateCodeFilter一个原理，用于验证短信验证码是否正确使用
 * author: chenshoujiang
 * date: 2019/12/6
 */
@Component
public class SmsCodeFilter extends OncePerRequestFilter {

    private static final String processUrl = "/login/mobile";

    private static final String processMethod = "POST";

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (processUrl.equals(httpServletRequest.getRequestURI())
                && processMethod.equals(httpServletRequest.getMethod())) {
            try {
                validateCode(httpServletRequest);
            } catch (ValidateCodeException e) {
                authFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validateCode(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String code = request.getParameter("code");
        SmsCode smsCode = (SmsCode)request.getSession().getAttribute("smsCode");

        if(StringUtils.isEmpty(code)) {
            throw new ValidateCodeException("请输入验证码");
        }
        if(StringUtils.isEmpty(mobile)) {
            throw new ValidateCodeException("请输入手机号");
        }
        if(null == smsCode) {
            throw new ValidateCodeException("无效的验证码");
        }
        if(!mobile.equals(smsCode.getMobile())) {
            throw new ValidateCodeException("验证码错误");
        }
        if(smsCode.isExpired()) {
            throw new ValidateCodeException("验证码以过期");
        }
        if(!smsCode.getCode().equalsIgnoreCase(code)) {
            throw new ValidateCodeException("验证码错误");
        }

        request.getSession().removeAttribute("smsCode");
    }
}
