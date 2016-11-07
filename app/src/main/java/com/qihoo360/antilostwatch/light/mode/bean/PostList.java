package com.qihoo360.antilostwatch.light.mode.bean;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HuirongZhang on 2016/10/27.
 */

public class PostList extends BaseBean {

    @SerializedName("feedslist")
    private List<PostBean> mPostList;

    public List<PostBean> getPostList() {
        return mPostList;
    }

}