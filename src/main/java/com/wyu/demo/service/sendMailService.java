package com.wyu.demo.service;

import com.wyu.demo.pojo.Entity.ToEmail;

public interface sendMailService {

    /**
     * 发送邮件【普通文字邮件】
     * @param toEmail
     */
    void sendMail(ToEmail toEmail);
}
