package com.qihoo360.antilostwatch.light.base;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by HuirongZhang on 2016/10/26.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements View.OnClickListener {
    public T mPresenter;

    public void createPresenter(T presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        }
    }
}