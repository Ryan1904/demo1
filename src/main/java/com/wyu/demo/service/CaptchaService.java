package com.wyu.demo.service;


public interface CaptchaService {
    /**
     * 登录密码错误次数增加
     * @param
     */
    void AddLoginFailCount(String username);

    /**
     * 登陆成功重置失败次数
     * @param username
     */
    void ResetLoginFailCount(String username);

    /**
     * 创建验证码
     * @return
     */
    String CreateCaptcha();

    /**
     * 获取登录次数
     * @param username
     * @return
     */
    Integer getLoginFailCount(String username);
}
