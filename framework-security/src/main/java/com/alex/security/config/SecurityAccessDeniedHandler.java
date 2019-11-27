package com.alex.security.config;

import com.alex.security.util.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: 权限不足处理器
 * author: chenshoujiang
 * date: 2019/11/25
 */
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if(WebUtil.isAjaxRequest(request)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("没有该权限！");
        } else {
            response.sendRedirect("/error/403");
        }
    }
}
