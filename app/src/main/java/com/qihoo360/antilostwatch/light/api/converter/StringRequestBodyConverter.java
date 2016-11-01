package com.qihoo360.antilostwatch.light.api.converter;

import android.text.TextUtils;
import android.util.Base64;

import com.qihoo360.antilostwatch.light.utils.EncryptRC4;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import retrofit2.Converter;

/**
 * Created by HuirongZhang on 2016/10/31.
 */

public class StringRequestBodyConverter<T> implements Converter<T, String> {

    @Override
    public String convert(T value) throws IOException {
        String count = String.valueOf(value);
        String p = getURLEncoder(encryptRc4(getEncodeParam(Integer.valueOf(count))));
        String result = "token=&p=" + p;
        return result;
    }

    public static String encryptRc4(String source) {
        return encryptRc4(source, getEncryptKey());
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

    public static String getEncryptKey() {
        String token = "";
        String qid = "";
        String rc4key = MD5Utils.encode(token + MD5Utils.encode(qid));
        return rc4key;
    }

    private String getEncodeParam(int count) {
        String timestamp = getTimestamp();
        String m2 = "add72710293144865e2d8c053bf3b28a";
        String source = "count=" + count + "timestamp=" + getURLEncoder(timestamp) + "&" + "m2=" + getURLEncoder(m2);
        return source;
    }

    public static String getTimestamp() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000); // 时间戳（单位秒）
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
}