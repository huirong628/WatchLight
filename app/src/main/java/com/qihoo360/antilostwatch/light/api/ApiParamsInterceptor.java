package com.qihoo360.antilostwatch.light.api;

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

public class ApiParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        RequestBody requestBody = new FormBody.Builder()
                .add("timestamp", getURLEncoder(getTimestamp()))
                .add("m2", getURLEncoder(getM2()))
                .build();
        String postBodyString = bodyToString(request.body());
        postBodyString += (postBodyString.length() > 0) ? "&" : "" + bodyToString(requestBody);
        request = requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString)).build();
        Response response = chain.proceed(request);
        return response;
    }

    /**
     * timestamp
     *
     * @return
     */
    private static String getTimestamp() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000);
    }

    private static String getM2() {
        return "add72710293144865e2d8c053bf3b28a";
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
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

    protected String decryptResult(byte[] result) throws Exception {
        String resultStr = decryptResultFromServer(result, null);
        return resultStr;
    }

    public static String decryptResultFromServer(byte[] result, String rc4Key) throws Exception {
        if (result == null) {
            return null;
        }
        String jsonStr = "";
        String temp = "";
        temp = new String(result, "UTF-8");
        temp = temp.trim();
        if (temp.startsWith("{") && temp.endsWith("}")) {
            jsonStr = temp;
        } else if (temp.startsWith("<html>") && temp.endsWith("</html>")) {
            jsonStr = "";
        } else {
            //jsonStr = decryptResult(result, rc4Key);
        }
        return jsonStr;
    }
}
