package com.qihoo360.antilostwatch.light.api;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    Observable<Response<ResponseBody>> queryByGet(@Url String url);

    @GET
    Observable<Response<ResponseBody>> queryByGet(@HeaderMap Map<String, String> header, @Url String url);

    @POST
    Observable<Response<ResponseBody>> queryByPost(@HeaderMap Map<String, String> header, @Url String url, @Body RequestBody requestBody);

    @Multipart
    Observable<Response<ResponseBody>> queryByMultipart(@HeaderMap Map<String, String> header, @Url String url, @Part MultipartBody multipartBody);
}