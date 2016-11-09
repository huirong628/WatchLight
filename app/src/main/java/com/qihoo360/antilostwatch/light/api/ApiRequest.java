package com.qihoo360.antilostwatch.light.api;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 * <p>
 * 对请求进行封装
 */

public class ApiRequest {
    public static final int REQUEST_METHOD_GET = 0;
    public static final int REQUEST_METHOD_POST = 1;
    public static final int REQUEST_METHOD_MULTIPART = 2;

    //请求方法
    //请求地址
    //header
    //请求参数
    private int method = REQUEST_METHOD_POST;//默认请求方法为：post
    private String url;
    private ApiHeader headers;
    private ApiParam param;

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHeaders(ApiHeader headers) {
        this.headers = headers;
    }

    public ApiHeader getHeaders() {
        return headers;
    }

    public ApiParam getParam() {
        return param;
    }

    public void setParam(ApiParam param) {
        this.param = param;
    }
}
