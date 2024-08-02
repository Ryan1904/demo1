package com.wyu.demo.pojo.util;

import java.util.Random;

public class CaptchaUtil {
    public static String CreateCaptcha() {
        Random random = new Random();
        int captcha = 1000 + random.nextInt(9000); // 生成四位随机验证码
        return String.valueOf(captcha);
    }
}
