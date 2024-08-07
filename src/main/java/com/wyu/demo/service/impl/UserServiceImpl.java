package com.wyu.demo.service.impl;

import com.wyu.demo.mapper.UserMapper;
import com.wyu.demo.pojo.constant.JwtClaimsConstant;
import com.wyu.demo.pojo.constant.MessageConstant;
import com.wyu.demo.pojo.Entity.User;
import com.wyu.demo.pojo.exception.AccountNotFoundException;
import com.wyu.demo.pojo.exception.CaptchaErrorException;
import com.wyu.demo.pojo.exception.PasswordErrorException;
import com.wyu.demo.pojo.exception.RegisterErrorException;
import com.wyu.demo.pojo.util.JwtUtil;
import com.wyu.demo.pojo.vo.UserLoginVO;
import com.wyu.demo.pojo.vo.UserRegisterVO;
import com.wyu.demo.service.CaptchaService;
import com.wyu.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CaptchaService captchaService;

    public User login(UserLoginVO userLoginVO) throws PasswordErrorException, CaptchaErrorException, AccountNotFoundException {
        String username = userLoginVO.getUsername();
        String password = userLoginVO.getPassword();
        String captcha = userLoginVO.getCaptcha();

        User user_login = userMapper.getByUsername(username);

        //TODO 验证码写死为1234
        if (!"1234".equals(captcha) ){
            throw new CaptchaErrorException(MessageConstant.CAPTCHA_ERROR);
        }

        //处理各种异常情况
        if (user_login == null){
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND, true);
        }

        // 验证码逻辑（如果失败次数超过5次，则需要验证码）
        if (user_login.getLoginFailCount() > 5 && !"1234".equals(captcha)) {
            captchaService.AddLoginFailCount(username);
            throw new CaptchaErrorException(MessageConstant.CAPTCHA_ERROR);
        }

        // 使用MD5加密用户输入的密码
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info("token:{}",encryptedPassword);

        // 数据库中的密码已经加密，直接比较加密后的用户输入密码和数据库中的密码
        if (!encryptedPassword.equals(user_login.getToken())) {
            captchaService.AddLoginFailCount(username); // 更新失败次数
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        // 登录成功，重置失败次数
        captchaService.ResetLoginFailCount(username);

        return user_login;
    }


    public void Register(UserRegisterVO userRegisterVO) throws RegisterErrorException {
        // 检查用户名是否已存在
        User UserByUsername = userMapper.getByUsername(userRegisterVO.getUsername());
        if (UserByUsername != null) {
            throw new RegisterErrorException(MessageConstant.USERNAME_ALREADY_EXISTS);
        }

        // 验证用户名
        if (userRegisterVO.getUsername() == null || userRegisterVO.getUsername().length() < 3) {
            throw new RegisterErrorException(MessageConstant.USERNAME_TOO_SHORT);
        }

        // 验证手机号格式
        if (userRegisterVO.getPhone() == null || userRegisterVO.getPhone().length() != 11) {
            throw new RegisterErrorException(MessageConstant.PHONE_FORMAT_ERROR);
        }

        // 验证密码
        if (userRegisterVO.getPassword() == null || userRegisterVO.getPassword().length() < 6) {
            throw new RegisterErrorException(MessageConstant.PASSWORD_TOO_SHORT);
        }

        // 检查手机号是否已存在
        User existingUserByPhone = userMapper.getByPhone(userRegisterVO.getPhone());
        if (existingUserByPhone != null) {
            throw new RegisterErrorException(MessageConstant.PHONE_ALREADY_EXISTS);
        }

        User user = new User();
        //对象属性拷贝
        BeanUtils.copyProperties(userRegisterVO, user);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USERNAME, user.getUsername());

        // 使用JWT工具类
        String token = JwtUtil.generateJwt(claims);
        user.setToken(token);


        userMapper.userRegister(user);
    }

}
