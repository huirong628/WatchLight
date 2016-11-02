package com.qihoo360.antilostwatch.light.api.service;

import com.qihoo360.antilostwatch.light.data.bean.talkbean.PostList;
import com.qihoo360.antilostwatch.light.data.bean.talkbean.TopicList;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by HuirongZhang on 2016/11/2.
 */

public interface TalkApiService {
    @GET("talk/recommend/refresh")
    Observable<PostList> loadPostList(@QueryMap Map<String, String> options);

    @GET("talk/recommend/more")
    Observable<PostList> loadMorePostList(@QueryMap Map<String, String> options);

    @GET("talk/topic/refreshhottesttopic")
    Observable<TopicList> loadTopicList(@QueryMap Map<String, String> options);

    @GET("talk/topic/morehottesttopic")
    Observable<TopicList> loadMoreTopicList(@QueryMap Map<String, String> options);
}