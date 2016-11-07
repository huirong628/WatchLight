package com.qihoo360.antilostwatch.light.api;


/**
 * Created by HuirongZhang on 2016/10/25.
 * <p>
 * 网络请求统一的一个入口
 */

public class ApiWrapper extends Api {

    private ApiService mApiService;

    public ApiWrapper() {
        mApiService = getRetrofit().create(ApiService.class);
    }

    public void query() {
        mApiService.query();
    }
}