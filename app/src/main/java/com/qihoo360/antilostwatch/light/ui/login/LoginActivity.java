package com.qihoo360.antilostwatch.light.ui.login;

import android.os.Bundle;

import com.qihoo360.antilostwatch.light.R;
import com.qihoo360.antilostwatch.light.base.BaseActivity;
import com.qihoo360.antilostwatch.light.utils.ActivityUtils;

/**
 * Created by HuirongZhang on 2016/10/24.
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), loginFragment, R.id.contentFrame);
        loginFragment.createPresenter(new LoginPresenter(loginFragment));
    }
}