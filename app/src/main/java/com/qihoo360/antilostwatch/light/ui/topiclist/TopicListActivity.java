package com.qihoo360.antilostwatch.light.ui.topiclist;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qihoo360.antilostwatch.light.R;
import com.qihoo360.antilostwatch.light.base.BaseActivity;
import com.qihoo360.antilostwatch.light.ui.postlist.PostListFragment;
import com.qihoo360.antilostwatch.light.ui.postlist.PostListPresenter;
import com.qihoo360.antilostwatch.light.utils.ActivityUtils;

/**
 * Created by HuirongZhang on 2016/11/2.
 */

public class TopicListActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        TopicListFragment topicFragment = (TopicListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (topicFragment == null) {
            topicFragment = TopicListFragment.getInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), topicFragment, R.id.contentFrame);
        topicFragment.createPresenter(new TopicListPresenter(topicFragment));
    }
}
