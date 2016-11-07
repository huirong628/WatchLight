package com.qihoo360.antilostwatch.light.mode.biz;

import com.qihoo360.antilostwatch.light.base.BaseBiz;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 */

public class TalkBiz extends BaseBiz {

    public void loadPostList() {
        mApiWrapper.query();
    }

    public void loadMorePostList() {

    }
}