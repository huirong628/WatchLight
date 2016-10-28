package com.qihoo360.antilostwatch.light.ui.login;

import com.qihoo360.antilostwatch.light.api.ApiCallBack;
import com.qihoo360.antilostwatch.light.base.BaseCommonPresenter;

import rx.Subscription;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class LoginPresenter extends BaseCommonPresenter<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String name, String pwd) {
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
