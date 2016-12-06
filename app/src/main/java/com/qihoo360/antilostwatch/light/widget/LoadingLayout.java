package com.qihoo360.antilostwatch.light.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.qihoo360.antilostwatch.light.R;


/**
 * Created by HuirongZhang
 * on 2016/12/5.
 */

public class LoadingLayout extends FrameLayout {

    public static final int STATUS_LOADING = 0;
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_NETWORK = 2;
    public static final int STATUS_EMPTY = 3;
    public static final int STATUS_ERROR = 4;

    /**
     * 显示加载中
     */
    private View mLoadingView;
    /**
     * 显示内容
     */
    private View mContentView;
    /**
     * 显示空
     */
    private View mEmptyView;
    /**
     * 显示网络问题
     */
    private View mNetworkView;
    /**
     * 显示错误
     */
    private View mErrorView;

    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        mLoadingView = inflater.inflate(R.layout.loading_layout, null, false);
        mEmptyView = inflater.inflate(R.layout.empty_layout, null, false);
        mNetworkView = inflater.inflate(R.layout.network_layout, null, false);
        mErrorView = inflater.inflate(R.layout.error_layout, null, false);
    }

    /**
     * inflating a view from XML
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        System.out.println("onFinishInflate()");
        if (getChildCount() > 1) {
            throw new IllegalStateException("LoadingLayout can only hold one child.");
        }
        this.addView(mLoadingView);
        this.addView(mEmptyView);
        this.addView(mNetworkView);
        this.addView(mErrorView);
    }

    /**
     * 设置当前状态
     *
     * @param status
     */
    public void setStatus(int status) {
        switch (status) {
            case STATUS_LOADING:
                showLoadingView();
                break;
            case STATUS_SUCCESS:
                showContentView();
                break;
            case STATUS_NETWORK:
                showNetworkView();
                break;
            case STATUS_EMPTY:
                showEmptyView();
                break;
            case STATUS_ERROR:
                showErrorView();
                break;
            default:
                break;
        }
    }

    /**
     *
     */
    private void showContentView() {
        mLoadingView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
        mNetworkView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
    }

    private void showLoadingView() {
        mLoadingView.setVisibility(VISIBLE);
        mEmptyView.setVisibility(GONE);
        mNetworkView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
    }

    /**
     * 无数据
     */
    private void showEmptyView() {
        mLoadingView.setVisibility(GONE);
        mEmptyView.setVisibility(VISIBLE);
        mNetworkView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
    }

    /**
     * 网络出现问题，无网络。
     */
    private void showNetworkView() {
        mLoadingView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
        mNetworkView.setVisibility(VISIBLE);
        mErrorView.setVisibility(GONE);
    }

    /**
     * 出现不可预知的错误了
     */
    private void showErrorView() {
        mLoadingView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
        mNetworkView.setVisibility(GONE);
        mErrorView.setVisibility(VISIBLE);
    }
}