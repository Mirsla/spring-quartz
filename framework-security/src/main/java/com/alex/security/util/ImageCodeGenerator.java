package com.alex.security.util;

import com.alex.security.entity.ImageCode;

/**
 * description:
 * author: chenshoujiang
 * date: 2019/12/4
 */
public interface ImageCodeGenerator {

    /**
     * 创建验证码图片
     * @return ImageCode 验证码相关信息
     */
    ImageCode createValidateCode();
}
