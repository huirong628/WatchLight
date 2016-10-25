package com.qihoo360.antilostwatch.light.ui.login;

import com.qihoo360.antilostwatch.light.account.Login;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;

    public LoginPresenter(LoginContract.View loginView) {
        mLoginView = loginView;
        mLoginView.setPresenter(this);
    }

    @Override
    public void login(String name, String pwd) {
        Login login = new Login(name, MD5Utils.getMD5code(pwd));
        login.doLogin();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
