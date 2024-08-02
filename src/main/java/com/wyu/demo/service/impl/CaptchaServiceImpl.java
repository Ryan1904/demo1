package com.wyu.demo.service.impl;

import com.wyu.demo.mapper.UserMapper;
import com.wyu.demo.pojo.User;
import com.wyu.demo.pojo.util.CaptchaUtil;
import com.wyu.demo.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private UserMapper userMapper;

    public void AddLoginFailCount(String username) {
        User user = userMapper.getByUsername(username);
        if (user != null) {
            user.setLoginFailCount(user.getLoginFailCount() + 1);
            userMapper.updateLoginFailCount(user);
        }
    }

    public void ResetLoginFailCount(String username) {
        User user = userMapper.getByUsername(username);
        if (user != null) {
            user.setLoginFailCount(0);
            userMapper.updateLoginFailCount(user);
        }
    }

    public String CreateCaptcha() {
        return CaptchaUtil.CreateCaptcha();
    }

    public Integer getLoginFailCount(String username) {
        User user = userMapper.getByUsername(username);
        return user != null ? user.getLoginFailCount() : 0;
    }
}
