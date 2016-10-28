package com.qihoo360.antilostwatch.light.api;


import com.qihoo360.antilostwatch.light.data.model.PostList;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class ApiWrapper extends Api {

    public void loadPostList(Observer<PostList> observer) {
        applySchedulers(getApiService().loadPostList(), observer);
    }

    public static <T> void applySchedulers(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }


}