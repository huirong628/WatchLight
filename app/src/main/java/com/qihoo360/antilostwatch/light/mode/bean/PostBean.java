package com.qihoo360.antilostwatch.light.mode.bean;


import com.google.gson.annotations.Expose;
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

    @Expose
    @SerializedName("image_url")
    private String[] imageUrl;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }
}
