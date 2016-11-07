package com.qihoo360.antilostwatch.light.api;


import android.text.TextUtils;
import android.util.Base64;

import com.qihoo360.antilostwatch.light.api.parameter.BaseApiParam;
import com.qihoo360.antilostwatch.light.api.parameter.TalkApiParam;
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

    public ApiWrapper() {
        mApiService = getRetrofit().create(ApiService.class);
    }

    public static ApiService getApiService() {
        return mApiService;
    }

    public void loadPostList(Observer<PostList> observer) {
        ApiParam param = new ApiParam.Builder().addParam(TalkApiParam.TIMESTAMP, getTimestamp())
                .addParam(TalkApiParam.M2, getM2())
                .addParam(TalkApiParam.COUNT, 20)
                .build();
        applySchedulers(getApiService().loadPostList(param.getFormatParams()), observer);
    }

    public void loadMorePostList() {

    }

    public static <T> void applySchedulers(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static String getTimestamp() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000); // 时间戳（单位秒）
    }

    public static String getM2() {
        return "add72710293144865e2d8c053bf3b28a";
    }


}