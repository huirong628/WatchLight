package com.qihoo360.antilostwatch.light.api;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 * 自定义异常
 */

public class ApiException extends Exception {
    private String code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
