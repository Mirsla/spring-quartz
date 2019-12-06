package com.alex.security.controller;

import com.alex.security.entity.ImageCode;
import com.alex.security.entity.SmsCode;
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

    /**
     * 发送短信验证码
     *  手机号校验啊这些先都不做，就实现基本的功能
     * @param mobile 手机号
     * @param request 请求 request
     */
    @GetMapping("/sms/code")
    public void createSmsCode(String mobile, HttpServletRequest request) {
        String verifyCode = String.valueOf((int)(Math.random()*900000 + 100000));
        System.out.println(verifyCode);
        SmsCode smsCode = new SmsCode(mobile, verifyCode, 60);
        request.getSession().setAttribute("smsCode",smsCode);
    }
}
