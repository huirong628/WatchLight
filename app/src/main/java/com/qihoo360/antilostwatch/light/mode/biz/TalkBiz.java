package com.qihoo360.antilostwatch.light.mode.biz;

import com.qihoo360.antilostwatch.light.api.ApiObserver;
import com.qihoo360.antilostwatch.light.api.ApiParam;
import com.qihoo360.antilostwatch.light.api.ApiRequest;
import com.qihoo360.antilostwatch.light.base.BaseBiz;
import com.qihoo360.antilostwatch.light.mode.bean.PostList;
import com.qihoo360.antilostwatch.light.utils.ApiUtil;

import java.io.IOException;

import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 * 圈子部分业务处理
 */

public class TalkBiz extends BaseBiz {
    private static final String RECOMMEND_REFRESH_URL = "http://m.baby.360.cn/talk/recommend/refresh";

    public TalkBiz() {
        super();
    }

    /*public void loadPostList(ApiObserver observer) {
        System.out.println("TalkBiz,loadPostList()");
        ApiRequest request = new ApiRequest();
        request.setMethod(ApiRequest.REQUEST_METHOD_GET);
        request.setUrl(RECOMMEND_REFRESH_URL);
        ApiParam param = new ApiParam.Builder()
                .addParam("count", 20)
                .build();
        request.setParam(param);
        mApiWrapper.query(request, observer);
    }*/

    public Observable<PostList> loadPostList() {
        ApiRequest request = new ApiRequest();
        request.setMethod(ApiRequest.REQUEST_METHOD_GET);
        request.setUrl(RECOMMEND_REFRESH_URL);
        ApiParam param = new ApiParam.Builder()
                .addParam("count", 20)
                .build();
        request.setParam(param);
        return mApiWrapper.query(request).flatMap(new Func1<Response, Observable<PostList>>() {
            @Override
            public Observable<PostList> call(Response response) {
                System.out.println("call,response.code = " + response.code());
                byte[] bytes = new byte[0];
                String json = null;
                try {
                    bytes = response.raw().body().bytes();
                    json = ApiUtil.decryptResult(bytes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Observable.just(mGson.fromJson(json, PostList.class));
            }
        });
    }
}