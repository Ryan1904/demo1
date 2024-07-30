package com.wyu.demo.pojo.exception;


/**
 * 密码错误异常
 */
public class PasswordErrorException extends Throwable {
    public PasswordErrorException() {
    }

    public PasswordErrorException(String msg) {
        super(msg);
    }
}
