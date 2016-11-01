package com.qihoo360.antilostwatch.light.ui.postlist;

import android.text.TextUtils;
import android.util.Base64;

import com.qihoo360.antilostwatch.light.base.BaseCommonPresenter;
import com.qihoo360.antilostwatch.light.data.bean.PostList;
import com.qihoo360.antilostwatch.light.utils.EncryptRC4;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import rx.Observer;


/**
 * Created by HuirongZhang on 2016/10/26.
 */

public class PostListPresenter extends BaseCommonPresenter<PostListFragment> implements PostListContract.Presenter {

    public PostListPresenter(PostListFragment view) {
        super(view);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void loadPostList() {
        //向服务端发送请求，获取数据，更新UI。
        mApiWrapper.mOptions.put("count", String.valueOf(20));
        mApiWrapper.loadPostList(new Observer<PostList>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError(),e.getMessage() = " + e.getMessage());
            }

            @Override
            public void onNext(PostList postList) {
                mView.onPostListLoaded(postList);
                System.out.println("onNext(),retcode = " + postList.getRetCode() + ",errcode = " + postList.getErrCode());
            }
        });
    }
}