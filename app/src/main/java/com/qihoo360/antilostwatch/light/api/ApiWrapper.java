package com.qihoo360.antilostwatch.light.api;


import com.google.gson.Gson;
import com.qihoo360.antilostwatch.light.utils.ApiUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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

    static final String MEDIA_TYPE = "application/x-www-form-urlencoded; charset=utf-8";
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

    /**
     * @param request 请求封装
     * @param type    对象类型
     * @param <T>     返回类型
     * @return 观察者
     */
    public <T> Observable<T> query(ApiRequest request, final Type type) {
        System.out.println("ApiWrapper.query() " + Thread.currentThread().getName());
        return Observable.just(request)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<ApiRequest, Observable<Response<ResponseBody>>>() {
                    @Override
                    public Observable<Response<ResponseBody>> call(ApiRequest request) {
                        System.out.println("current thread 1 " + Thread.currentThread().getName());
                        return getApiResponse(request);
                    }
                })
                .flatMap(new Func1<Response<ResponseBody>, Observable<T>>() {
                    @Override
                    public Observable<T> call(Response<ResponseBody> response) {
                        System.out.println("current thread 2 " + Thread.currentThread().getName());
                        //解析放回的结果
                        String jsonStr = handlerResponse(response);
                        System.out.println(jsonStr);
                        return Observable.just((T) mGson.fromJson(jsonStr, type));
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
        System.out.println("ApiWrapper.getApiResponse() " + Thread.currentThread().getName());
        Observable<Response<ResponseBody>> observable = null;
        int method = request.getMethod();
        String url = request.getUrl();
        Map<String, String> headers = request.getHeaders().getHeaders();
        if (method == ApiRequest.REQUEST_METHOD_GET) {
            System.out.println(request.getUrl() + "?" + request.getParam().getFormatParams());
            //observable = mApiService.queryByGet(request.getUrl() + "?" + request.getParam().getFormatParams());
            observable = mApiService.queryByGet(headers, url + "?" + request.getParam().getFormatParams());
        } else if (method == ApiRequest.REQUEST_METHOD_POST) {
            RequestBody requestBody = RequestBody.create(MediaType.parse(MEDIA_TYPE), request.getParam().getFormatParams());
            observable = mApiService.queryByPost(headers, url, requestBody);
        } else if (method == ApiRequest.REQUEST_METHOD_MULTIPART) {

        }
        System.out.println("getApiResponse().end");
        return observable;
    }

    /**
     * 统一处理返回结果
     *
     * @param response
     * @return
     */
    private String handlerResponse(Response<ResponseBody> response) {
        String responseResult = null;
        /** HTTP status code. */
        int code = response.code();
        try {
            if (code == 200) {
                ResponseBody body = response.body();
                if (true) {//返回数据加密
                    responseResult = ApiUtil.decryptResult(body.bytes());
                } else {
                    responseResult = body.string();
                }
            } else {
                responseResult = response.errorBody().string();
                int errorCode = 10000 + code;

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("retcode", 1);
                jsonObject.put("errcode", errorCode);
                jsonObject.put("msg", responseResult);
                responseResult = jsonObject.toString();
            }
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("retcode", 1);
                jsonObject.put("errcode", -1);
                jsonObject.put("msg", responseResult);
            } catch (JSONException ex) {

            }
            responseResult = jsonObject.toString();
        }
        return responseResult;
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