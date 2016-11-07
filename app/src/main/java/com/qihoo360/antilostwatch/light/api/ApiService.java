package com.qihoo360.antilostwatch.light.api;


import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by HuirongZhang on 2016/10/25.
 * <p>
 * 请求接口
 * <p>
 * 主要通过传递参数封装
 */

public interface ApiService {

    @GET
    Observable<Response> queryByGet(@Url String url);
}