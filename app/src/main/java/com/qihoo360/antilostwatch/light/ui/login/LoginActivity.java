package com.qihoo360.antilostwatch.light.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.qihoo360.antilostwatch.light.R;
import com.qihoo360.antilostwatch.light.utils.ActivityUtils;

/**
 * Created by HuirongZhang on 2016/10/24.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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