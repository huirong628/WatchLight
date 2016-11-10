package com.qihoo360.antilostwatch.light.mode.bean;


import com.google.gson.annotations.SerializedName;

/**
 * Created by HuirongZhang on 2016/10/27.
 * <p>
 * 帖子实体类
 */

public class PostBean {

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("summary")
    private String summary;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }
}
