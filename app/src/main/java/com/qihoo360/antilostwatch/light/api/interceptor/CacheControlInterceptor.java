package com.qihoo360.antilostwatch.light.api.interceptor;

import com.qihoo360.antilostwatch.light.utils.NetworkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HuirongZhang
 * on 2016/11/9.
 */

public class CacheControlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //对于request，没有网络时，使用缓存
        if (!NetworkUtil.isNetworkAvailable()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response originalResponse = chain.proceed(request);
        if (NetworkUtil.isNetworkAvailable()) {
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", CacheControl.FORCE_CACHE.toString())
                    .build();
        }
    }
}
