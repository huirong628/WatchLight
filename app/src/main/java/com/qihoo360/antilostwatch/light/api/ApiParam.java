package com.qihoo360.antilostwatch.light.api;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Converter;

/**
 * Created by HuirongZhang
 * on 2016/11/4.
 * <p>
 * 用来构建请求参数
 */

public final class ApiParam {
    private Map<String, Object> mParam = new HashMap<>();

    public ApiParam(Map<String, Object> params) {
        mParam.putAll(params);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, Object> entry : mParam.entrySet()) {
            if (result.length() > 0) result.append("&");
            result.append(entry.getKey()).append("=").append(entry.getValue());
        }
        System.out.println("ApiParam = " + result.toString());
        return result.toString();
    }

    public static final class Builder {
        private Map<String, Object> params = new HashMap<>();

        public Builder addParam(String key, String value) {
            params.put(key, value);
            return this;
        }

        public Builder addParam(String key, int value) {
            params.put(key, value);
            return this;
        }

        public ApiParam build() {
            return new ApiParam(params);
        }
    }
}
