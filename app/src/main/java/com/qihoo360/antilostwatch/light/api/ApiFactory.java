package com.qihoo360.antilostwatch.light.api;

import com.qihoo360.antilostwatch.light.api.service.ContactApiService;
import com.qihoo360.antilostwatch.light.api.service.PhotoApiService;
import com.qihoo360.antilostwatch.light.api.service.TalkApiService;
import com.qihoo360.antilostwatch.light.api.service.WiFiApiService;

import static com.qihoo360.antilostwatch.light.api.Api.getRetrofit;

/**
 * Created by HuirongZhang on 2016/11/2.
 */

public class ApiFactory {
    private static ApiService mApiService;
    private static TalkApiService mTalkApiService;
    private static ContactApiService mContactApiService;
    private static PhotoApiService mPhotoApiService;
    private static WiFiApiService mWiFiApiService;

    public ApiFactory() {
        mApiService = getRetrofit().create(ApiService.class);
        mTalkApiService = getRetrofit().create(TalkApiService.class);
        mContactApiService = getRetrofit().create(ContactApiService.class);
        mPhotoApiService = getRetrofit().create(PhotoApiService.class);
        mWiFiApiService = getRetrofit().create(WiFiApiService.class);
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
}
