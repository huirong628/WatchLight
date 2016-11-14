package com.qihoo360.antilostwatch.light.mode.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HuirongZhang
 * on 2016/11/14.
 */

public class TopicList extends BaseBean {
    @Expose
    @SerializedName("hottest_topic")
    private List<TopicBean> hotTopicList;

    public List<TopicBean> getHotTopicList() {
        return hotTopicList;
    }
}
