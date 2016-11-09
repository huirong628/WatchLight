package com.qihoo360.antilostwatch.light.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 * <p>
 * 对Header进行封装
 */

public final class ApiHeader {
    private Map<String, String> mHeaders = new HashMap<>();

    private ApiHeader(Map<String, String> headers) {
        mHeaders.put("User-Agent", "kids-Android-5.3.2");
        mHeaders.putAll(headers);
    }

    protected Map<String, String> getHeaders() {
        return mHeaders;
    }

    public static final class Builder {
        private Map<String, String> headers = new HashMap<>();

        public Builder addHeader(String key, String value) {
            headers.put(key, value);
            return this;
        }

        public ApiHeader build() {
            return new ApiHeader(headers);
        }
    }
}
