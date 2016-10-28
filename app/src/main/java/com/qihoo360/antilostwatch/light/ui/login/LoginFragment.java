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

public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {

    private EditText mNameET;
    private EditText mPwdET;
    private Button mLoginBtn;

    public static LoginFragment newInstance() {
        return new LoginFragment();
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

    @Override
    public void success() {
        Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
    }
}
