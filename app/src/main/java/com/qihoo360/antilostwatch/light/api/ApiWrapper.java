package com.qihoo360.antilostwatch.light.api;


import com.qihoo360.antilostwatch.light.data.bean.PostList;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HuirongZhang on 2016/10/25.
 * <p>
 * 主要用到rx
 */

public class ApiWrapper extends Api {

    public void loadPostList(String token, String p, Observer<PostList> observer) {
        applySchedulers(getApiService().loadPostList(token, p), observer);
    }

    public void loadPostList(int count, Observer<PostList> observer) {
        applySchedulers(getApiService().loadPostList(count), observer);
    }

    public static <T> void applySchedulers(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}