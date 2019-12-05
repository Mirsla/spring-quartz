package com.alex.security.controller;

import com.alex.security.entity.ImageCode;
import com.alex.security.util.ImageCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * description: 验证码的控制器
 * author: chenshoujiang
 * date: 2019/12/5
 */
@Controller
public class ValidateCodeController {
    @Autowired
    private ImageCodeGenerator imageCodeGenerator;

    @GetMapping("/validate/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = imageCodeGenerator.createValidateCode();
        HttpSession session = request.getSession();
        ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());
        imageCode.setImage(null);
        session.setAttribute("validateCode", imageCode);
    }
}
