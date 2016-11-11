package com.qihoo360.antilostwatch.light.ui.postlist;

import android.os.Bundle;

import com.qihoo360.antilostwatch.light.R;
import com.qihoo360.antilostwatch.light.base.BaseActivity;
import com.qihoo360.antilostwatch.light.utils.ActivityUtils;

/**
 * Created by HuirongZhang on 2016/10/26.
 */

public class PostListActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        PostListFragment postFragment = (PostListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (postFragment == null) {
            postFragment = postFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), postFragment, R.id.contentFrame);
        postFragment.createPresenter(new PostListPresenter(postFragment));
    }
}
