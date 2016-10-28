package com.qihoo360.antilostwatch.light.api;


import com.qihoo360.antilostwatch.light.data.model.PostList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by HuirongZhang on 2016/10/25.
 * <p>
 * ApiService:统一管理网络接口
 */

public interface ApiService {

    //圈子部分

    /**
     * 获取帖子列表
     * <p>
     * FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).
     *
     * @Field parameters can only be used with form encoding
     */
    @GET("/talk/recommend/more")
    Observable<PostList> loadPostList();
}