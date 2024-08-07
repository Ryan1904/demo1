package com.wyu.demo.service.impl;

import com.wyu.demo.pojo.Entity.ToEmail;
import com.wyu.demo.service.sendMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class sendMailServiceImpl implements sendMailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(ToEmail toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(toEmail.getFrom());
        message.setTo(toEmail.getTos());
        message.setSubject(toEmail.getSubject());
        message.setSubject(toEmail.getSubject());
        message.setText(toEmail.getText());
        mailSender.send(message);
        log.info("发送成功");
    }
}
