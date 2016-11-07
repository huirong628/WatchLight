package com.qihoo360.antilostwatch.light.api;


import retrofit2.Response;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 * <p>
 * 对响应进行封装
 */

public class ApiResponse {
    //响应码
    //响应信息
    //响应头
    //响应体
    private Response response;
    private int code;
    private String message;
    private String headers;
    private String body;

    public String getBody() {
        return body;
    }

    public int getCode() {
        return code;
    }
}