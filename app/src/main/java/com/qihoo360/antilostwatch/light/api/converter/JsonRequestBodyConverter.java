package com.qihoo360.antilostwatch.light.api.converter;


import android.text.TextUtils;
import android.util.Base64;

import com.qihoo360.antilostwatch.light.utils.EncryptRC4;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by HuirongZhang on 2016/10/30.
 */

public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    public JsonRequestBodyConverter() {

    }

    @Override
    public RequestBody convert(T value) throws IOException {
        String param = value.toString();
        System.out.println("convert = " + param);
        //处理token 和 p
        String token = "";
        String p = getURLEncoder(encryptRc4());
        String result = "token=&p=" + p;
        System.out.println("JsonRequestBodyConverter.result =" + result);
        return RequestBody.create(MEDIA_TYPE, result);
    }

    private String encryptRc4() {
        return encryptRc4(getEncodeParam());
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

    private String getEncodeParam() {
        String timestamp = getTimestamp();
        String m2 = "add72710293144865e2d8c053bf3b28a";
        String source = "timestamp=" + getURLEncoder(timestamp) + "&" + "m2=" + getURLEncoder(m2);
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
