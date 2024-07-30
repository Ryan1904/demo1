package com.wyu.demo.service;

import com.wyu.demo.pojo.User;
import com.wyu.demo.pojo.exception.CaptchaErrorException;
import com.wyu.demo.pojo.exception.PasswordErrorException;
import com.wyu.demo.pojo.vo.UserLoginVO;

import javax.security.auth.login.AccountNotFoundException;

public interface UserService {

    /**
     * 用户登录
     * @param userLoginVO
     */
    User login(UserLoginVO userLoginVO) throws  AccountNotFoundException, PasswordErrorException, CaptchaErrorException;
}
