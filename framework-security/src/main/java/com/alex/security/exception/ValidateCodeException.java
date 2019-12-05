package com.alex.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * description: 图片验证码的自定义异常
 * author: chenshoujiang
 * date: 2019/12/4
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
