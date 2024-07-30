package com.wyu.demo.controller;

import com.wyu.demo.pojo.Result.Result;
import com.wyu.demo.pojo.User;
import com.wyu.demo.pojo.constant.JwtClaimsConstant;
import com.wyu.demo.pojo.constant.MessageConstant;
import com.wyu.demo.pojo.exception.CaptchaErrorException;
import com.wyu.demo.pojo.exception.PasswordErrorException;
import com.wyu.demo.pojo.util.JwtUtil;
import com.wyu.demo.pojo.vo.UserLoginVO;
import com.wyu.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sys")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<UserLoginVO> UserLogin(@RequestBody UserLoginVO userLoginVO) {
        log.info("用户登录操作");
        try {
            User login = userService.login(userLoginVO); // 登录成功后，生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtClaimsConstant.USERNAME, login.getUsername());

            // 使用JWT工具类
            String token = JwtUtil.generateJwt(claims);
            log.info("用户令牌为:{}", token);

            User user = User.builder()
                    .id(login.getId())
                    .username(login.getUsername())
                    .token(token)
                    .build();

            return Result.success(user);
        } catch (CaptchaErrorException e) {
            return Result.error(MessageConstant.Captcha_ERROR, 500, false); //TODO codeShow写死为false
        } catch (PasswordErrorException e) {
            return Result.error(MessageConstant.PASSWORD_ERROR, 500, false); //TODO codeShow写死为false
        } catch (AccountNotFoundException e) {
            return Result.error(MessageConstant.ACCOUNT_NOT_FOUND, 500, false); //TODO codeShow写死为false
        } catch (Exception e) {
            log.error("Unknown error: ", e);
            return Result.error("Unknown error occurred", 500, false);
        }
    }
}
