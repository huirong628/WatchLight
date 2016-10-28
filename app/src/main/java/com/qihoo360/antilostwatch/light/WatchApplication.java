package com.qihoo360.antilostwatch.light;

import android.app.Application;

/**
 * Created by HuirongZhang on 2016/10/26.
 */

public class WatchApplication extends Application {

    private static WatchApplication mInstance;

    public static WatchApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
