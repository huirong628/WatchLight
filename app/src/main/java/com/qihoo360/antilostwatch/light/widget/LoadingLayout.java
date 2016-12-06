package com.qihoo360.antilostwatch.light.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by HuirongZhang
 * on 2016/12/5.
 */

public class LoadingLayout extends FrameLayout {

    /**
     * 显示加载中
     */
    private View mLoadingLayout;
    /**
     * 显示内容
     */
    private View mContentLayout;
    /**
     * 显示空
     */
    private View mEmptyLayout;
    /**
     * 显示网络问题
     */
    private View mNetworkLayout;
    /**
     * 显示错误
     */
    private View mErrorLayout;

    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * inflating a view from XML
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
