package com.qihoo360.antilostwatch.light.mode.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HuirongZhang
 * on 2016/11/14.
 */

public class TopicBean {
    @SerializedName("id")
    private long id;

    @SerializedName("topic_name")
    private String name;

    @SerializedName("topic_desc")
    private String desc;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
