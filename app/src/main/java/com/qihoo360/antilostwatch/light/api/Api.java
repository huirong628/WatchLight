package com.qihoo360.antilostwatch.light.api;


import com.qihoo360.antilostwatch.light.WatchApplication;
import com.qihoo360.antilostwatch.light.api.interceptor.UserAgentInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class Api {

    /**
     * Retrofit2 的baseUlr 必须以 /（斜线） 结束，不然会抛出一个IllegalArgumentException,
     */
    private static final String BASE_URL = "http://m.baby.360.cn/";

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private static ApiService mApiService;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    private static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            // log拦截器  打印所有的log
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 请求的缓存
            File cacheFile = new File(WatchApplication.getInstance().getCacheDir(), "cache");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb


            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(new UserAgentInterceptor())
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .cache(cache)
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .build();
        }
        return mRetrofit;
    }

    /**
     * 获取OkHttpClient对象
     *
     * @return
     */
    private static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
        return mOkHttpClient;
    }

    /**
     * 获取ApiService
     *
     * @return
     */
    protected static ApiService getApiService() {
        mApiService = getRetrofit().create(ApiService.class);
        return mApiService;
    }

    public static class APIException extends Exception {
        public String code;
        public String message;

        public APIException(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

    }
}
