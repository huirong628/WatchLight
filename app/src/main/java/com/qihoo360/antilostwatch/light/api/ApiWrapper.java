package com.qihoo360.antilostwatch.light.api;


import com.google.gson.Gson;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by HuirongZhang on 2016/10/25.
 * <p>
 * 网络请求统一的一个入口
 */

public class ApiWrapper<T> extends Api {

    private ApiService mApiService;
    private final Gson mGson;

    public ApiWrapper() {
        mApiService = getRetrofit().create(ApiService.class);
        mGson = new Gson();
    }

    public void query(ApiRequest request, ApiObserver observer) {
        System.out.println("ApiWrapper.query()");
        Observable.just(request)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<ApiRequest, Observable<Response>>() {
                    @Override
                    public Observable<Response> call(ApiRequest request) {
                        return getApiResponse(request);
                    }
                })
                .flatMap(new Func1<Response, Observable<T>>() {
                    @Override
                    public Observable<T> call(Response response) {
                        System.out.println("call,response.code = " + response.code());
                        return null;
                    }
                })
                .observeOn(Schedulers.immediate())
                .subscribe(observer);
    }

    private Observable<Response> getApiResponse(ApiRequest request) {
        System.out.println("getApiResponse()");
        Observable<Response> observable = null;
        int method = request.getMethod();
        if (method == ApiRequest.REQUEST_METHOD_GET) {
            System.out.println(request.getUrl() + "?" + request.getParam().getFormatParams());
            observable = mApiService.queryByGet(request.getUrl() + "?" + request.getParam().getFormatParams());
        } else if (method == ApiRequest.REQUEST_METHOD_POST) {

        } else if (method == ApiRequest.REQUEST_METHOD_MULTIPART) {

        }
        return observable;
    }
}