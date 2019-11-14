package com.alex.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Alex
 * @descripTion: 登录控制器
 * @date: Created in  22:42 2019/11/12
 * @modified By:
 */
@Controller
public class LoginController {
    @RequestMapping("login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("toLogin")
    public String login(String username, String password) {
        System.out.println(username);
        System.out.println(password);

        return "index";
    }
}
