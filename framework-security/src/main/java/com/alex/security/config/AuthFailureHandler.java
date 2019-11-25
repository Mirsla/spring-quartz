package com.alex.security.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * description: 登录失败的处理器
 *      这里使用登录失败处理器，而不使用直接跳转错误页面的原因是为了更好的用户体验，在此跳转到登录页面的时候可以带入更过的信息
 * author: alex
 * date: 2019/11/25
 */
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
//        System.out.println(request.getParameter("username"));
        request.setAttribute("username",request.getParameter("username"));
//        System.out.println(exception.getMessage());
//        response.sendRedirect("/login?error=true&username="+request.getParameter("username"));

        request.getRequestDispatcher("/login?error=true").forward(request,response);
    }
}
