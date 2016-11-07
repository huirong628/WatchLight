package com.qihoo360.antilostwatch.light.api;


/**
 * Created by HuirongZhang
 * on 2016/11/7.
 * <p>
 * 对响应进行封装
 */

public class ApiResponse<T> {
    //响应码
    //响应信息
    //响应头
    //响应体
    private int code;
    private String message;
    private String headers;
    private String body;

    public String getBody() {
        return body;
    }
}