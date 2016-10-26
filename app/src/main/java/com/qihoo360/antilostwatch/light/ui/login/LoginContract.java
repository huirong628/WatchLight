package com.qihoo360.antilostwatch.light.ui.login;

import com.qihoo360.antilostwatch.light.base.BasePresenter;
import com.qihoo360.antilostwatch.light.base.BaseView;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void login(String name, String pwd);
    }
}