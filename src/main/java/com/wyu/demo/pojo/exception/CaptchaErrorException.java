package com.wyu.demo.pojo.exception;

public class CaptchaErrorException extends Exception {
    public CaptchaErrorException() {
        super();
    }

    public CaptchaErrorException(String msg) {
        super(msg);
    }
}
