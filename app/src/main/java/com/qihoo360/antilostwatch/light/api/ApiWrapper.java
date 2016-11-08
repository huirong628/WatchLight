package com.qihoo360.antilostwatch.light.api;


import com.google.gson.Gson;
import com.qihoo360.antilostwatch.light.utils.ApiUtil;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by HuirongZhang on 2016/10/25.
 * <p>
 * 网络请求统一的一个入口
 */

public class ApiWrapper extends Api {

    private final ApiService mApiService;
    private final Gson mGson;

    public ApiWrapper() {
        mApiService = getRetrofit().create(ApiService.class);
        mGson = new Gson();
    }

    /*public void query(ApiRequest request, ApiObserver<T> observer) {
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
    }*/

    public <T> Observable<T> query(ApiRequest request, final Type type) {
        return Observable.just(request)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<ApiRequest, Observable<Response<ResponseBody>>>() {
                    @Override
                    public Observable<Response<ResponseBody>> call(ApiRequest request) {
                        return getApiResponse(request);
                    }
                })
                .flatMap(new Func1<Response<ResponseBody>, Observable<T>>() {
                    @Override
                    public Observable<T> call(Response<ResponseBody> response) {
                        byte[] bytes = new byte[0];
                        String json = null;
                        try {
                            bytes = response.body().bytes();
                            json = ApiUtil.decryptResult(bytes);
                            System.out.println("json = " + json);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return Observable.just((T) mGson.fromJson(json, type));
                    }
                });
    }

    public Observable<Response<ResponseBody>> query(ApiRequest request) {
        System.out.println("ApiWrapper.query()");
        return Observable.just(request)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<ApiRequest, Observable<Response<ResponseBody>>>() {
                    @Override
                    public Observable<Response<ResponseBody>> call(ApiRequest request) {
                        return getApiResponse(request);
                    }
                });
    }

    private Observable<Response<ResponseBody>> getApiResponse(ApiRequest request) {
        System.out.println("getApiResponse().start");
        Observable<Response<ResponseBody>> observable = null;
        int method = request.getMethod();
        if (method == ApiRequest.REQUEST_METHOD_GET) {
            System.out.println(request.getUrl() + "?" + request.getParam().getFormatParams());
            observable = mApiService.queryByGet(request.getUrl() + "?" + request.getParam().getFormatParams());
        } else if (method == ApiRequest.REQUEST_METHOD_POST) {

        } else if (method == ApiRequest.REQUEST_METHOD_MULTIPART) {

        }
        System.out.println("getApiResponse().end");
        return observable;
    }

    protected <T> Observable<T> applySchedulers(Observable<T> responseObservable) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<T, Observable<T>>() {
                    @Override
                    public Observable<T> call(T tResponse) {
                        return flatResponse(tResponse);
                    }
                });
    }

    public <T> Observable<T> flatResponse(final T response) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (response != null) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(response);
                    }
                } else {
                    if (!subscriber.isUnsubscribed()) {
                        //subscriber.onError(new APIException("自定义异常类型", "解析json错误或者服务器返回空的json"));
                    }
                    return;
                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
    }
}