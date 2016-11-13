package com.qihoo360.antilostwatch.light.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HuirongZhang on 2016/10/26.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements View.OnClickListener {
    public Context mContext;
    public T mPresenter;

    //返回布局资源ID
    public abstract int getLayoutId();

    //初始化fragment中的view
    public abstract void initView(View view);

    //创建对应的Presenter
    public void createPresenter(T presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        }
    }

    /**
     * Fragment与Activity关联后立刻调用
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    /**
     * Bundle对象中获取一些在Activity中传过来的数据
     * 通常会在该方法中读取保存的状态，获取或初始化一些数据。
     * 在该方法中不要进行耗时操作，不然窗口不会显示。
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * @param inflater:用来装载布局文件
     * @param container:<fragment>标签的父标签对应对象
     * @param savedInstanceState:获取Fragment保存的状态,如果未保存那么就为null
     * @return Fragment显示的View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    /**
     * @param view:onCreateView中返回的view
     * @param savedInstanceState:用于一般用途
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    /**
     * 表示窗口已经初始化完成
     * 可以在Fragment中使用getActivity().findViewById(Id);来操控Activity中的view了
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 当系统调用该方法的时候，fragment已经显示在ui上
     * 但还不能进行互动，因为onResume方法还没执行完
     */
    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * fragment可以与用户互动
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * fragment有活跃变为不活跃
     */
    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * fragment将从屏幕上消失。
     */
    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * fragment不再使用
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }
}