package com.alex.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description:
 * author: chenshoujiang
 * date: 2019/11/25
 */
@Controller
@RequestMapping("error")
public class ErrorController {

    @RequestMapping("403")
    public String errorPage403() {
        return "error403";
    }
}
