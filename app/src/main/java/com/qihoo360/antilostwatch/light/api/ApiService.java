package com.qihoo360.antilostwatch.light.api;


import com.qihoo360.antilostwatch.light.mode.bean.PostList;
import com.qihoo360.antilostwatch.light.data.bean.talkbean.TopicList;


import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by HuirongZhang on 2016/10/25.
 * <p>
 *     请求接口
 *
 *     主要通过传递参数封装
 *
 */

public interface ApiService {

    @GET
    Observable<ApiResponse> query();
}