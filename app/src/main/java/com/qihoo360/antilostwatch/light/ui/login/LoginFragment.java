package com.qihoo360.antilostwatch.light.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qihoo360.antilostwatch.light.R;
import com.qihoo360.antilostwatch.light.base.BaseFragment;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {

    private EditText mNameET;
    private EditText mPwdET;
    private Button mLoginBtn;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.login_fragment;
    }

    @Override
    public void initView(View view) {
        mNameET = (EditText) view.findViewById(R.id.name_et);
        mPwdET = (EditText) view.findViewById(R.id.pwd_et);
        mLoginBtn = (Button) view.findViewById(R.id.bt_go);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //todo login check
        if (v == mLoginBtn) {
            mPresenter.login(getActivity(), mNameET.getText().toString(), mPwdET.getText().toString());
        }
    }

    @Override
    public void success() {
        Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
    }
}
