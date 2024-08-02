package com.wyu.demo.controller;

import com.wyu.demo.pojo.Result.Result;
import com.wyu.demo.pojo.User;
import com.wyu.demo.pojo.constant.JwtClaimsConstant;
import com.wyu.demo.pojo.constant.MessageConstant;
import com.wyu.demo.pojo.exception.CaptchaErrorException;
import com.wyu.demo.pojo.exception.PasswordErrorException;
import com.wyu.demo.pojo.util.JwtUtil;
import com.wyu.demo.pojo.vo.UserLoginVO;
import com.wyu.demo.service.CaptchaService;
import com.wyu.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sys")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CaptchaService captchaService;



    @PostMapping("/login")
    public Result<User> UserLogin(@RequestBody UserLoginVO userLoginVO) {
        log.info("用户登录操作");
        try {
            User login = userService.login(userLoginVO); // 登录成功后，生成jwt令牌
            captchaService.ResetLoginFailCount(userLoginVO.getUsername()); // 重置登录失败次数

            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtClaimsConstant.USERNAME, login.getUsername());

            // 使用JWT工具类
            String token = JwtUtil.generateJwt(claims);
            Integer expire = JwtUtil.getExpire();
            log.info("用户令牌为:{}", token);

            return Result.success(token,expire);
        } catch (CaptchaErrorException e) {
            return Result.error(MessageConstant.Captcha_ERROR, 500, true);
        } catch (PasswordErrorException e) {
            Integer failCount = captchaService.getLoginFailCount(userLoginVO.getUsername());
            if (failCount >= 5) {
                String captcha = captchaService.CreateCaptcha(); //TODO 不用返回给前端？
                return Result.error(MessageConstant.PASSWORD_ERROR, 500, true);
            }
            return Result.error(MessageConstant.PASSWORD_ERROR, 500, false);
        } catch (AccountNotFoundException e) {
            return Result.error(MessageConstant.ACCOUNT_NOT_FOUND, 500, false);
        } catch (Exception e) {
            log.error("Unknown error: ", e);
            return Result.error("Unknown error occurred", 500, false);
        }
    }

}
