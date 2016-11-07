package com.qihoo360.antilostwatch.light.api.parameter;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by HuirongZhang
 * on 2016/11/3.
 */

public abstract class BaseApiParam {
    public static final String TOKEN = "token";//用户token
    public static final String PARAM = "p";//加密后的原始请求参数

    private static Map<String, Object> mParams = new HashMap<>();

    public static Map<String, Object> getParams() {
        return mParams;
    }
}