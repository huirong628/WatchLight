package com.qihoo360.antilostwatch.light.api;


import com.qihoo360.antilostwatch.light.BuildConfig;
import com.qihoo360.antilostwatch.light.WatchApplication;
import com.qihoo360.antilostwatch.light.api.converter.JsonConverterFactory;
import com.qihoo360.antilostwatch.light.api.interceptor.UserAgentInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class Api {

    /**
     * Retrofit2 的baseUlr 必须以 /（斜线） 结束，不然会抛出一个IllegalArgumentException,
     */
    private static final String BASE_URL = "http://m.baby.360.cn/";

    private static OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofit;

    private static void initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        File cacheFile = new File(WatchApplication.getInstance().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

        builder.addInterceptor(new UserAgentInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .cache(cache);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        mOkHttpClient = builder.build();
    }

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    protected static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            initOkHttpClient();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(mOkHttpClient)
                    .addConverterFactory(JsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
