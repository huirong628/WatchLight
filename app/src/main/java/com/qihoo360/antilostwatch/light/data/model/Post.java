package com.qihoo360.antilostwatch.light.data.model;


import com.google.gson.Gson;

/**
 * Created by HuirongZhang on 2016/10/27.
 */

public class Post {
    private int id;
    private String title;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
