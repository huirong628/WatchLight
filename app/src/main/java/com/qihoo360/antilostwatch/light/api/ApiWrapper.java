package com.qihoo360.antilostwatch.light.api;


import android.text.TextUtils;
import android.util.Base64;

import com.qihoo360.antilostwatch.light.api.service.ContactApiService;
import com.qihoo360.antilostwatch.light.api.service.PhotoApiService;
import com.qihoo360.antilostwatch.light.api.service.TalkApiService;
import com.qihoo360.antilostwatch.light.api.service.WiFiApiService;
import com.qihoo360.antilostwatch.light.data.bean.talkbean.PostList;
import com.qihoo360.antilostwatch.light.utils.EncryptRC4;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HuirongZhang on 2016/10/25.
 * <p>
 * 主要用到rx
 */

public class ApiWrapper extends Api {

    private static ApiService mApiService;
    private static TalkApiService mTalkApiService;
    private static ContactApiService mContactApiService;
    private static PhotoApiService mPhotoApiService;
    private static WiFiApiService mWiFiApiService;

    public ApiWrapper() {
        mApiService = getRetrofit().create(ApiService.class);
    }

    public static ApiService getApiService() {
        return mApiService;
    }

    public static TalkApiService getTalkApiService() {
        return mTalkApiService;
    }

    public static ContactApiService getContactApiService() {
        return mContactApiService;
    }

    public static PhotoApiService getPhotoApiService() {
        return mPhotoApiService;
    }

    public static WiFiApiService getWiFiApiService() {
        return mWiFiApiService;
    }



    public Map<String, String> mOptions = new HashMap<String, String>();

    public void loadPostList(Observer<PostList> observer) {
        mOptions.put("timestamp", getTimestamp());
        mOptions.put("m2", getM2());
        String p = getURLEncoder(encryptRc4(getEncodeParam()));
        Map<String, String> map = new HashMap<>();
        map.put("p", p);
        map.put("token", "");
        applySchedulers(getApiService().loadPostList(map), observer);
    }

    public void loadMorePostList() {

    }

    public static <T> void applySchedulers(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
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
        StringBuffer buffer = new StringBuffer();
        for (String key : mOptions.keySet()) {
            String value = mOptions.get(key);
            if (value == null) {
                value = "";
            }
            buffer.append(key + "=" + getURLEncoder(value));
            buffer.append("&");
        }
        String source = buffer.toString();
        source = source.substring(0, source.length() - 1);
        return source;
    }

    public static String getTimestamp() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000); // 时间戳（单位秒）
    }

    public static String getM2() {
        return "add72710293144865e2d8c053bf3b28a";
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