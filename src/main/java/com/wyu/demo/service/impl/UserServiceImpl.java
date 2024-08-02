package com.wyu.demo.service.impl;

import com.wyu.demo.mapper.UserMapper;
import com.wyu.demo.pojo.constant.MessageConstant;
import com.wyu.demo.pojo.User;
import com.wyu.demo.pojo.exception.CaptchaErrorException;
import com.wyu.demo.pojo.exception.PasswordErrorException;
import com.wyu.demo.pojo.vo.UserLoginVO;
import com.wyu.demo.service.CaptchaService;
import com.wyu.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.security.auth.login.AccountNotFoundException;

@Slf4j
@Service
@Transactional

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CaptchaService captchaService;

    public User login(UserLoginVO userLoginVO) throws AccountNotFoundException, PasswordErrorException, CaptchaErrorException {
        String username = userLoginVO.getUsername();
        String password = userLoginVO.getPassword();
        String captcha = userLoginVO.getCaptcha();

        User user_login = userMapper.getByUsername(username);

        //TODO 验证码写死为1234
        if (!"1234".equals(captcha) ){
            throw new CaptchaErrorException(MessageConstant.Captcha_ERROR);
        }

        //处理各种异常情况
        if (user_login == null){
            //账号不存在
            captchaService.AddLoginFailCount(username);
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 验证码逻辑（如果失败次数超过5次，则需要验证码）
        if (user_login.getLoginFailCount() > 5 && !"1234".equals(captcha)) {
            throw new CaptchaErrorException(MessageConstant.Captcha_ERROR);
        }

        // 使用MD5加密用户输入的密码
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info("token:{}",encryptedPassword);

        // 数据库中的密码已经加密，直接比较加密后的用户输入密码和数据库中的密码
        if (!encryptedPassword.equals(user_login.getToken())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        return user_login;
    }

}
