package com.alex.security.util;

import javax.servlet.http.HttpServletRequest;

/**
 * description: web相关工具类
 * author: chenshoujiang
 * date: 2019/11/27
 */
public class WebUtil {

    /**
     * check 当前请求是否为ajax请求
     * @param request HttpServletRequest
     * @return boolean true 为ajax请求， false为其他请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
