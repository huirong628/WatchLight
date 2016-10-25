package com.qihoo360.antilostwatch.light.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.qihoo360.antilostwatch.light.R;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    private EditText mNameET;
    private EditText mPwdET;
    private Button mLoginBtn;

    private LoginContract.Presenter mPresenter;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment, container, false);
        mNameET = (EditText) root.findViewById(R.id.name_et);
        mPwdET = (EditText) root.findViewById(R.id.pwd_et);
        mLoginBtn = (Button) root.findViewById(R.id.login_btn);
        mLoginBtn.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        //todo login check
        if (v == mLoginBtn) {
            mPresenter.login(mNameET.getText().toString(), mPwdET.getText().toString());
        }
    }
}
