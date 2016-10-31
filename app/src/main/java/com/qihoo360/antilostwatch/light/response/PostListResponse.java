package com.qihoo360.antilostwatch.light.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.qihoo360.antilostwatch.light.data.bean.PostBean;

import java.util.List;

/**
 * Created by HuirongZhang on 2016/10/29.
 */

public class PostListResponse extends BaseResponse {

    @Expose
    @SerializedName("feedslist")
    private List<PostBean> mPostList;


}
