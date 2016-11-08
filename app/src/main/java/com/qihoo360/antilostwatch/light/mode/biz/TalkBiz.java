package com.qihoo360.antilostwatch.light.mode.biz;

import com.qihoo360.antilostwatch.light.api.ApiParam;
import com.qihoo360.antilostwatch.light.api.ApiRequest;
import com.qihoo360.antilostwatch.light.base.BaseBiz;
import com.qihoo360.antilostwatch.light.mode.bean.PostList;
import com.qihoo360.antilostwatch.light.utils.ApiUtil;


import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
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
        request.setMethod(ApiRequest.REQUEST_METHOD_GET);
        request.setUrl(RECOMMEND_REFRESH_URL);
        ApiParam param = new ApiParam.Builder()
                .addParam("count", 20)
                .build();
        request.setParam(param);
        return mApiWrapper.query(request).flatMap(new Func1<Response<ResponseBody>, Observable<PostList>>() {

            @Override
            public Observable<PostList> call(Response<ResponseBody> responseBodyResponse) {
                System.out.println("call,response.code = " + responseBodyResponse.code());
                byte[] bytes = new byte[0];
                String json = null;
                try {
                    bytes = responseBodyResponse.body().bytes();
                    json = ApiUtil.decryptResult(bytes);
                    System.out.println("json = " + json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Observable.just(mGson.fromJson(json, PostList.class));
            }
        });
    }
}