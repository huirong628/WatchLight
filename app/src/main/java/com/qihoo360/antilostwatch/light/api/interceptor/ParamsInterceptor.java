package com.qihoo360.antilostwatch.light.api.interceptor;

import android.text.TextUtils;
import android.util.Base64;

import com.qihoo360.antilostwatch.light.utils.EncryptRC4;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by HuirongZhang on 2016/10/28.
 * <p>
 * 自定义拦截器
 * <p>
 * 每一次请求带上一个或者多个固定不变的参数
 * <p>
 * 如：timestamp，m2
 */

public class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        Response response = chain.proceed(request);

        //处理token 和 p
        String token = "";
        String p = getURLEncoder(encryptRc4());
        String result = "token=&p=" + p;
        System.out.println("result = " + result);

        
        return response;
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
