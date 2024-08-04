package com.wyu.demo.pojo.exception;

/**
 * 验证码错误异常
 */
public class CaptchaErrorException extends Exception {
    public CaptchaErrorException() {
        super();
    }

    public CaptchaErrorException(String msg) {
        super(msg);
    }
}
