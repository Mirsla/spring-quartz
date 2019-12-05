package com.alex.security.entity;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * description: 图片码，不仅仅限制于 验证码，还有其他地方的码也可以使用
 * author: chenshoujiang
 * date: 2019/12/4
 */
public class ImageCode {

    // 图片流
    private BufferedImage image;

    // 码的实际值
    private String code;

    // 过期时间，这个可要可不要，看需求
    private LocalDateTime expireTime;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public ImageCode(BufferedImage image, String code, int expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }
}
