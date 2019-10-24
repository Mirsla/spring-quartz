package com.alex.job.extend;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class JobSpringMvcConfig implements WebMvcConfigurer {

    /**
     * 注册错误页面
     * @return ErrorPageRegistrar
     */
//    @Bean
    public ErrorPageRegistrar errorPageRegistry() {
        return (registry) -> {
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/WEB-INF/views/error/error404.jsp");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/views/error/error404.jsp");
            ErrorPage error405Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/WEB-INF/views/error/error404.jsp");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/WEB-INF/views/error/error500.jsp");
            registry.addErrorPages(error403Page, error404Page, error405Page,error500Page);
        };
    }

}
