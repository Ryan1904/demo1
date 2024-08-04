package com.wyu.demo.pojo.exception;

public class AccountNotFoundException extends Exception {
    private boolean codeShow;

    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String message,boolean codeShow) {
        super(message);
        this.codeShow = codeShow;
    }

}
