package com.qihoo360.antilostwatch.light.mode.biz;

import com.qihoo360.antilostwatch.light.api.ApiHeader;
import com.qihoo360.antilostwatch.light.api.ApiParam;
import com.qihoo360.antilostwatch.light.api.ApiRequest;
import com.qihoo360.antilostwatch.light.base.BaseBiz;
import com.qihoo360.antilostwatch.light.mode.bean.PostList;


import rx.Observable;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 * 圈子部分业务处理
 */

public class TalkBiz extends BaseBiz {
    private static final String RECOMMEND_REFRESH_URL = "http://218.30.118.227/talk/recommend/refresh";
    private static final String RECOMMEND_MORE_URL = "http://218.30.118.227/talk/recommend/more";

    private static final String KEY_COUNT = "count";
    private static final String KEY_LAST_ID = "last_id";

    public TalkBiz() {
        super();
    }

   /* public void loadPostList(ApiObserver observer) {
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
        ApiHeader header = new ApiHeader.Builder().build();
        ApiParam param = new ApiParam.Builder()
                .addParam(KEY_COUNT, 20)
                .build();
        request.setMethod(ApiRequest.REQUEST_METHOD_GET);
        request.setUrl(RECOMMEND_REFRESH_URL);
        request.setHeaders(header);
        request.setParam(param);
        return mApiWrapper.query(request, PostList.class);
    }

    public Observable<PostList> loadMorePostList(long id) {
        ApiRequest request = new ApiRequest();
        ApiHeader header = new ApiHeader.Builder().build();
        request.setMethod(ApiRequest.REQUEST_METHOD_GET);
        request.setUrl(RECOMMEND_MORE_URL);
        ApiParam param = new ApiParam.Builder()
                .addParam(KEY_COUNT, 20)
                .addParam(KEY_LAST_ID, id)
                .build();
        request.setParam(param);
        request.setHeaders(header);
        return mApiWrapper.query(request, PostList.class);
    }
}