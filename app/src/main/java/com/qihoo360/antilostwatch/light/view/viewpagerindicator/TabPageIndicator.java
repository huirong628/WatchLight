package com.qihoo360.antilostwatch.light.view.viewpagerindicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
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
    private int mSelectedTabIndex;
    private int mMaxTabWidth;

    public TabPageIndicator(Context context) {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTabLayout = new IcsLinearLayout(context, 0);
        addView(mTabLayout, new LayoutParams(WRAP_CONTENT, MATCH_PARENT));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("TabPageIndicator,onMeasure()");
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
        setFillViewport(lockedExpanded);

        final int childCount = mTabLayout.getChildCount();
        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            if (childCount > 2) {
                mMaxTabWidth = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
            } else {
                mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
        } else {
            mMaxTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth) {
            // Recenter the tab display if we're at a new (scrollable) size.
            setCurrentIndex(mSelectedTabIndex);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mViewPager != null) {
            mViewPager.addOnPageChangeListener(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mViewPager != null) {
            mViewPager.removeOnPageChangeListener(this);
        }
    }

    /**
     * bind the ViewPager with TabPageIndicator
     * <p>
     * 点击上面的Tab，下面的ViewPager切换；滑动ViewPager，上面的Tab跟着切换。
     *
     * @param view is bound to TabPageIndicator
     */
    @Override
    public void setViewPager(ViewPager view) {
        if (view == mViewPager) {
            return;
        }
        System.out.println("TabPageIndicator,setViewPager()");
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
        setCurrentIndex(mSelectedTabIndex);
        requestLayout();
    }

    /**
     * @param index
     */
    private void setCurrentIndex(int index) {
        mSelectedTabIndex = index;
        mViewPager.setCurrentItem(index);
        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final View child = mTabLayout.getChildAt(i);
            final boolean isSelected = (index == i);
            child.setSelected(isSelected);
        }
    }

    /**
     * add tab to the TabLayout
     *
     * @param index of the title
     * @param text  of the fragment
     */
    private void addTab(int index, CharSequence text) {
        final TabView tabView = new TabView(getContext());
        tabView.setGravity(Gravity.CENTER);
        tabView.setIndex(index);
        tabView.setTitle(text);
        tabView.setMaxTabWidth(mMaxTabWidth);
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final int newSelectedIndex = tabView.getIndex();
                /**
                 * 从第一个到最后一个后会有滑屏的效果，即切换时会有切换时间。
                 *
                 * false to transition immediately
                 */
                mViewPager.setCurrentItem(newSelectedIndex, false);
            }
        });
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
        System.out.println("TabPageIndicator,onPageScrolled(),position =" + position);
    }

    /**
     * This method will be invoked when a new page becomes selected. Animation is not
     * necessarily complete.
     *
     * @param position Position index of the new selected page.
     */
    @Override
    public void onPageSelected(int position) {
        System.out.println("TabPageIndicator,onPageSelected(),position =" + position);
        setCurrentIndex(position);
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
        System.out.println("TabPageIndicator,onPageScrollStateChanged(),state =" + state);
    }
}
