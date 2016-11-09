package com.qihoo360.antilostwatch.light.api;


import com.qihoo360.antilostwatch.light.BuildConfig;
import com.qihoo360.antilostwatch.light.WatchApplication;
import com.qihoo360.antilostwatch.light.api.interceptor.CacheControlInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by HuirongZhang
 * on 2016/10/25.
 */

public class Api {

    /**
     * Retrofit2 的baseUlr 必须以 /（斜线） 结束，不然会抛出一个IllegalArgumentException,
     */
    private static final String BASE_URL = "http://m.baby.360.cn/";
    private static final long CACHE_SIZE = 1024 * 1024 * 50;

    private static OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofit;

    private static void initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //set cache dir
        File cacheFile = new File(WatchApplication.getInstance().getCacheDir(), "watch_cache");
        Cache cache = new Cache(cacheFile, CACHE_SIZE);

        builder.connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(cache)
                .addNetworkInterceptor(new CacheControlInterceptor());

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        mOkHttpClient = builder.build();
    }

    /**
     * @return Retrofit对象
     */
    protected static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            initOkHttpClient();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(mOkHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
