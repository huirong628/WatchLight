package com.qihoo360.antilostwatch.light.api.parameter;

/**
 * Created by HuirongZhang
 * on 2016/11/3.
 */

public abstract class BaseApiParam {
    private String token;//用户token
    private String p;//加密后的原始请求参数
    //原始请求参数具体字段
    private String timestamp;
    private String m2;
}