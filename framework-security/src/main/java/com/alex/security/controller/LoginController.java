package com.alex.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: Alex
 * @descripTion: 登录控制器
 * @date: Created in  22:42 2019/11/12
 * @modified By:
 */
@Controller
public class LoginController {


    @RequestMapping(value = "login")
    public ModelAndView loginPage(Boolean error, String username) {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        model.addObject("error",error);
        model.addObject("username",username);
        return model;
    }

    @RequestMapping(value = "/logout/success")
    @ResponseBody
    public String logout() {
        return "退出登录成功，这是/logout/success";
    }

//    @RequestMapping(value = "toLogin", method = RequestMethod.POST)
//    public String login(Boolean error, String username) {
//        System.out.println(username);
//        return "index";
//    }
}
