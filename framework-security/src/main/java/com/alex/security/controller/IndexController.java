package com.alex.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description: 首页
 * author: chenshoujiang
 * date: 2019/11/11
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("menu")
    public String menu() {
        return "menu";
    }

    @RequestMapping("role")
    public String role() {
        return "role";
    }

    @RequestMapping("log")
    public String log() {
        return "log";
    }

    @RequestMapping("user")
    public String user() {
        return "user";
    }

    @RequestMapping("product")
    public String product() {
        return "product";
    }
}
