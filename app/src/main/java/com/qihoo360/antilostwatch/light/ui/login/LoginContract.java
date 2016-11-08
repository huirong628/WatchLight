package com.qihoo360.antilostwatch.light.ui.login;

import android.content.Context;

import com.qihoo360.antilostwatch.light.base.BasePresenter;
import com.qihoo360.antilostwatch.light.base.BaseView;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public interface LoginContract {

    interface View extends BaseView {
        void success();
    }

    interface Presenter extends BasePresenter {
        void login(Context context, String name, String pwd);
    }
}