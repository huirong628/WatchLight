package com.qihoo360.antilostwatch.light.api;


import com.qihoo360.antilostwatch.light.data.bean.PostList;


import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by HuirongZhang on 2016/10/25.
 * <p>
 * 主要用到：retrofit2
 * <p>
 * <p>
 * [1]ApiService:统一管理接口定义
 * <p>
 * [2]ApiService接口的实现是通过Retrofit生成的。
 * <p>
 * Retrofit retrofit = new Retrofit.Builder()
 * .baseUrl("https://api.github.com/")
 * .build();
 * ApiService service = retrofit.create(ApiService.class);
 */

public interface ApiService {

    //圈子部分

    /**
     * 获取推荐页帖子列表
     * <p>
     * FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).
     *
     * @Field parameters can only be used with form encoding
     * <p>
     * ***********************************************************************************
     * Use annotations to describe the HTTP request:
     */
    @GET("http://m.baby.360.cn/talk/recommend/refresh")
    Observable<PostList> loadPostList(@Query("token") String token, @Query("p") String p);

    @POST("http://m.baby.360.cn/talk/recommend/refresh")
    Observable<PostList> loadPostList(@Body int count);
}