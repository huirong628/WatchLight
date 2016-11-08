package com.qihoo360.antilostwatch.light.ui.login;

import android.content.Context;
import android.content.Intent;

import com.qihoo360.antilostwatch.light.api.ApiCallBack;
import com.qihoo360.antilostwatch.light.base.BaseCommonPresenter;
import com.qihoo360.antilostwatch.light.ui.postlist.PostListActivity;

import rx.Subscription;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class LoginPresenter extends BaseCommonPresenter<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(Context context, String name, String pwd) {
        Intent intent = new Intent(context, PostListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
