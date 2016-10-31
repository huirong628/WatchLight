package com.qihoo360.antilostwatch.light.api.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HuirongZhang on 2016/10/31.
 * <p>
 * 创建设置UA的Interceptor
 */

public class UserAgentInterceptor implements Interceptor {
    private static final String USER_AGENT = "User-agent";

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request originalRequest = chain.request();
        final Request requestWithUserAgent = originalRequest.newBuilder()
                .removeHeader(USER_AGENT)
                .addHeader(USER_AGENT, "kids-Android-5.3.2")
                .build();
        return chain.proceed(requestWithUserAgent);
    }
}
