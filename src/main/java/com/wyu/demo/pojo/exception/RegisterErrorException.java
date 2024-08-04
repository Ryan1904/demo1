package com.wyu.demo.pojo.exception;

/**
 * 注册错误异常
 */
public class RegisterErrorException extends Throwable{
    public RegisterErrorException() {
    }

    public RegisterErrorException(String msg) {
        super(msg);
    }
}
