package com.qihoo360.antilostwatch.light.data.model;

import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HuirongZhang on 2016/10/27.
 */

public class PostList {

    @Expose
    @SerializedName("retcode")
    protected int retCode;

    @Expose
    @SerializedName("errcode")
    protected int errCode;

    @Expose
    @SerializedName("feedslist")
    private List<Post> mPostList;


    public List<Post> getPostList() {
        return mPostList;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}