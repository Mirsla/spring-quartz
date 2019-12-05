package com.alex.security.filter;

import com.alex.security.config.AuthFailureHandler;
import com.alex.security.entity.ImageCode;
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
 * description: 验证码的过滤器，在UsernamePasswordAuthenticationFilter之前执行，如果验证码不正确，不再进行下一步的校验
 * author: chenshoujiang
 * date: 2019/12/4
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    private static final String processUrl = "/login";

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
        String validateCode = request.getParameter("validateCode");
        ImageCode imageCode = (ImageCode)request.getSession().getAttribute("validateCode");

        if(StringUtils.isEmpty(validateCode)) {
            throw new ValidateCodeException("请输入验证码");
        }
        if(null == imageCode) {
            throw new ValidateCodeException("无效的验证码");
        }
        if(!imageCode.getCode().equalsIgnoreCase(validateCode)) {
            throw new ValidateCodeException("验证码错误");
        }

        request.getSession().removeAttribute("validateCode");
    }
}
