package com.qihoo360.antilostwatch.light.base;

import com.google.gson.Gson;
import com.qihoo360.antilostwatch.light.api.ApiWrapper;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 */

public class BaseBiz {
    protected ApiWrapper mApiWrapper;

    public BaseBiz() {
        mApiWrapper = new ApiWrapper();
    }
}
