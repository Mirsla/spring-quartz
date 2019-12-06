package com.alex.security.entity;

import java.time.LocalDateTime;

/**
 * description: 手机号验证码登录的验证码信息
 * author: chenshoujiang
 * date: 2019/12/6
 */
public class SmsCode {
    // 手机号
    private String mobile;

    //验证码
    private String code;

    // 过期时间
    private LocalDateTime expireTime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public SmsCode() {
    }

    public SmsCode(String mobile, String code, int expireIn){
        this.mobile=mobile;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 用于判断当前的短信验证码是否过期
     * @return boolean true 未过期， false 过期
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(getExpireTime());
    }
}
