package com.qihoo360.antilostwatch.light.view.viewpagerindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qihoo360.antilostwatch.light.ui.postlist.PostListFragment;
import com.qihoo360.antilostwatch.light.ui.postlist.PostListPresenter;
import com.qihoo360.antilostwatch.light.ui.user.UserFragment;

/**
 * Created by HuirongZhang
 * on 2016/11/28.
 */

public class TabPageIndicatorAdapter extends FragmentStatePagerAdapter {
    private static final String[] TITLE = {"首页", "消息", "发现", "我的"};

    public TabPageIndicatorAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            PostListFragment postFragment = PostListFragment.getInstance();
            postFragment.createPresenter(new PostListPresenter(postFragment));
            return postFragment;
        }
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString("title", TITLE[position]);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return TITLE.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position % TITLE.length];
    }
}
