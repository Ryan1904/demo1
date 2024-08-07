package com.wyu.demo.service;

import com.wyu.demo.pojo.Entity.User;
import com.wyu.demo.pojo.exception.CaptchaErrorException;
import com.wyu.demo.pojo.exception.PasswordErrorException;
import com.wyu.demo.pojo.exception.RegisterErrorException;
import com.wyu.demo.pojo.vo.UserLoginVO;
import com.wyu.demo.pojo.vo.UserRegisterVO;

import javax.security.auth.login.AccountNotFoundException;

public interface UserService {

    /**
     * 用户登录
     * @param userLoginVO
     */
    User login(UserLoginVO userLoginVO) throws AccountNotFoundException, PasswordErrorException, CaptchaErrorException, com.wyu.demo.pojo.exception.AccountNotFoundException;

    /**
     * 用户注册
     * @param userRegisterVO 注册信息
     * @throws RegisterErrorException
     */
    void Register(UserRegisterVO userRegisterVO) throws RegisterErrorException;
}
