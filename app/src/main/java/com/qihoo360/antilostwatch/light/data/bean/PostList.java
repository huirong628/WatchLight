package com.qihoo360.antilostwatch.light.data.bean;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HuirongZhang on 2016/10/27.
 */

public class PostList {

    @SerializedName("retcode")
    protected int retCode;

    @SerializedName("errcode")
    protected int errCode;

    @SerializedName("feedslist")
    private List<PostBean> mPostList;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public List<PostBean> getPostList() {
        return mPostList;
    }

    public void setPostList(List<PostBean> mPostList) {
        this.mPostList = mPostList;
    }
}