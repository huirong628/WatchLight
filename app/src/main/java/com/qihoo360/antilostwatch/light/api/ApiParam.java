package com.qihoo360.antilostwatch.light.api;


import android.text.TextUtils;
import android.util.Base64;

import com.qihoo360.antilostwatch.light.utils.ApiUtil;
import com.qihoo360.antilostwatch.light.utils.EncryptRC4;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Created by HuirongZhang
 * on 2016/11/4.
 * <p>
 * 用来构建请求参数
 */

public final class ApiParam {
    Map<String, Object> mParam = new HashMap<>();
    final boolean isNeedEncrypt;

    public ApiParam(Builder builder) {
        this.isNeedEncrypt = builder.isNeedEncrypt;
        mParam.put("timestamp", ApiUtil.getTimestamp());
        mParam.put("m2", ApiUtil.getM2());
        mParam.putAll(builder.params);
    }

    private String encryptRc4(String source) {
        return encryptRc4(source, getEncryptKey());
    }

    private String getEncryptKey() {
        String token = "";
        String qid = "";
        String rc4key = MD5Utils.encode(token + MD5Utils.encode(qid));
        return rc4key;
    }

    private String encryptRc4(String source, String rc4key) {
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
    //***************************

    /**
     * 解决中文参数乱码问题
     *
     * @param s
     * @return 编码之后的字符串
     */
    private String encode(String s) {
        String encoding = null;
        try {
            encoding = URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoding;
    }

    /**
     * @return 编码之后的参数字符串
     */
    private String getEncodedParams() {
        if (mParam == null || mParam.isEmpty()) {
            return "";
        }
        //优先使用该类，它比StringBuffer要快.
        StringBuilder builder = new StringBuilder();
        //获取Map中所有的键值对(key-value映射集合)
        Set<Map.Entry<String, Object>> entrySet = mParam.entrySet();
        //获取Map.Entry<String, Object>引用的唯一方法就是通过entrySet的迭代器来实现
        Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            if (builder.length() > 0) {
                builder.append("&");
            }
            if (entry.getValue() != null) {//添加保护，防止NPE，由于HashMap允许Key和Value为null
                builder.append(entry.getKey()).append("=").append(encode(entry.getValue().toString()));
            }
        }
        return builder.toString();
    }

    /**
     * @param param
     * @return 加密后的参数字符串
     */
    private String encryptParam(String param) {
        String token = "";
        StringBuilder builder = new StringBuilder();
        builder.append("token=").append(encode(token));
        if (!(param == null || param.length() == 0)) {
            String encryptPara = encryptRc4(param);
            builder.append("&")
                    .append("p=")
                    .append(encode(encryptPara));
        }
        return builder.toString();
    }

    public String getFormatParams() {
        String paramStr = getEncodedParams();
        if (isNeedEncrypt) {
            return encryptParam(paramStr);
        }
        return paramStr;
    }

    public static final class Builder {
        private Map<String, Object> params = new HashMap<>();
        boolean isNeedEncrypt;

        public Builder() {
            isNeedEncrypt = true;
        }

        public Builder addParam(String key, String value) {
            params.put(key, value);
            return this;
        }

        public Builder addParam(String key, long value) {
            params.put(key, value);
            return this;
        }

        public Builder addParam(String key, int value) {
            params.put(key, value);
            return this;
        }

        /**
         * 设置参数是否需要加密
         *
         * @param isNeedEncrypt
         * @return
         */
        public Builder isNeedEncrypt(boolean isNeedEncrypt) {
            this.isNeedEncrypt = isNeedEncrypt;
            return this;
        }

        public ApiParam build() {
            return new ApiParam(this);
        }
    }
}
