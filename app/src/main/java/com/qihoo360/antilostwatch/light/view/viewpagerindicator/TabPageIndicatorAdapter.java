package com.qihoo360.antilostwatch.light.view.viewpagerindicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by HuirongZhang
 * on 2016/11/28.
 */

public class TabPageIndicatorAdapter extends FragmentStatePagerAdapter {
    private static final String[] TITLE = {"生活", "军事", "NBA", "科技", "数码", "头条", "情感", "房产"};

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
        return null;
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
