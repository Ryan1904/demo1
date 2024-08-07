package com.wyu.demo.controller;

import com.wyu.demo.pojo.Entity.ToEmail;
import com.wyu.demo.pojo.Result.Result;
import com.wyu.demo.service.sendMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
@Slf4j
public class MailController {

    @Autowired
    private sendMailService sendMailService;

    @PostMapping("/sendMail")
    public Result sendMail(@RequestBody ToEmail toEmail){
        log.info("发送邮件操作");
        try{
            sendMailService.sendMail(toEmail);
            return Result.success();
        }catch (MailException e) {
            log.info("发送失败：{}",e);
            return Result.error("发送未成功，请检查后再重试", 500);
        }catch (Exception e) {
            log.error("Unknown error: ", e);
            return Result.error("Unknown error occurred", 500);
        }
    }
}
