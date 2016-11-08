package com.qihoo360.antilostwatch.light.api;


import android.text.TextUtils;
import android.util.Base64;

import com.qihoo360.antilostwatch.light.utils.EncryptRC4;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


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

    public String getFormatParams() {
        String paramStr = getUrlEncodeParams();
        return encryptParas(paramStr);
    }

    private String getUrlEncodeParams() {
        if (mParam == null || mParam.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (String key : mParam.keySet()) {
            String value = mParam.get(key).toString();
            if (value == null) {
                value = "";
            }
            builder.append(key).append("=").append(getURLEncoder(value)).append("&");
        }

        String paramStr = builder.toString();
        paramStr = paramStr.substring(0, paramStr.length() - 1);

        return paramStr;
    }

    public static String getURLEncoder(String content) {
        if (content == null || content.length() == 0) {
            return content;
        }
        try {
            content = URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return content;
    }

    private String encryptParas(String param) {
        String token = "";
        StringBuilder builder = new StringBuilder();
        builder.append("token=").append(getURLEncoder(token));
        if (!(param == null || param.length() == 0)) {
            String encryptPara = encryptRc4(param);
            builder.append("&");
            builder.append("p=");
            builder.append(getURLEncoder(encryptPara));
        }

        return builder.toString();
    }

    public static String encryptRc4(String source) {
        return encryptRc4(source, getEncryptKey());
    }

    public static String getEncryptKey() {
        String token = "";
        String qid = "";
        String rc4key = MD5Utils.encode(token + MD5Utils.encode(qid));
        return rc4key;
    }

    public static String encryptRc4(String source, String rc4key) {
        if (TextUtils.isEmpty(source)) {
            return "";
        }
        byte[] result = null;
        try {
            byte[] data = source.getBytes("UTF-8");
            result = EncryptRC4.make(rc4key, data);
        } catch (Exception e) {
        }
        String base64para = null;
        if (result != null) {
            base64para = new String(Base64.encode(result, Base64.NO_WRAP));
        }
        return base64para;
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