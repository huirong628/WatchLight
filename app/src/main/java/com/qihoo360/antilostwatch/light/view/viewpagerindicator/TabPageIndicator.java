package com.qihoo360.antilostwatch.light.view.viewpagerindicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import static android.widget.FrameLayout.LayoutParams.*;

/**
 * Created by HuirongZhang
 * on 2016/11/28.
 */

public class TabPageIndicator extends HorizontalScrollView implements PageIndicator {
    private IcsLinearLayout mTabLayout;
    private ViewPager mViewPager;

    public TabPageIndicator(Context context) {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTabLayout = new IcsLinearLayout(context, attrs);
        addView(mTabLayout, new LayoutParams(WRAP_CONTENT, MATCH_PARENT));
    }

    /**
     * bind the ViewPager with TabPageIndicator
     *
     * @param view is bound to TabPageIndicator
     */
    @Override
    public void setViewPager(ViewPager view) {
        this.mViewPager = view;
        notifyDataSetChanged();
    }

    private void notifyDataSetChanged() {
        mTabLayout.removeAllViews();
        PagerAdapter adapter = mViewPager.getAdapter();
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            CharSequence title = adapter.getPageTitle(i);
            addTab(i, title);
        }
        requestLayout();
    }

    /**
     * add tab to the TabLayout
     *
     * @param index of the title
     * @param text  of the fragment
     */
    private void addTab(int index, CharSequence text) {
        TabView tabView = new TabView(getContext());
        tabView.index = index;
        tabView.setText(text);
        mTabLayout.addView(tabView, new LinearLayout.LayoutParams(0, MATCH_PARENT, 1));
    }

    /**
     * This method will be invoked when the current page is scrolled, either as part
     * of a programmatically initiated smooth scroll or a user initiated touch scroll.
     *
     * @param position             Position index of the first page currently being displayed.
     *                             Page position+1 will be visible if positionOffset is nonzero.
     * @param positionOffset       Value from [0, 1) indicating the offset from the page at position.
     * @param positionOffsetPixels Value in pixels indicating the offset from position.
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * This method will be invoked when a new page becomes selected. Animation is not
     * necessarily complete.
     *
     * @param position Position index of the new selected page.
     */
    @Override
    public void onPageSelected(int position) {

    }

    /**
     * Called when the scroll state changes. Useful for discovering when the user
     * begins dragging, when the pager is automatically settling to the current page,
     * or when it is fully stopped/idle.
     *
     * @param state The new scroll state.
     * @see ViewPager#SCROLL_STATE_IDLE
     * @see ViewPager#SCROLL_STATE_DRAGGING
     * @see ViewPager#SCROLL_STATE_SETTLING
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
