package com.qihoo360.antilostwatch.light.api;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 * <p>
 * 对请求进行封装
 */

public class ApiRequest {

    //请求方法
    //请求地址
    //header
    //请求参数
    private String method;
    private String url;
    private String headers;
    private ApiParam param;

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public void setParam(ApiParam param) {
        this.param = param;
    }
}
