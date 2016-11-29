package com.qihoo360.antilostwatch.light.view.viewpagerindicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qihoo360.antilostwatch.light.R;

/**
 * Created by HuirongZhang
 * on 2016/11/29.
 */

public class TabView extends LinearLayout {
    private TextView mTabTitle;
    private ImageView mTabIcon;
    private int index;
    private int maxTabWidth;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * error here
         *
         *
         */
        LayoutInflater.from(context).inflate(R.layout.tab_view_layout, this, true);
        mTabTitle = (TextView) findViewById(R.id.tab_title);
        mTabIcon = (ImageView) findViewById(R.id.tab_icon);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Re-measure if we went beyond our maximum size.
        if (maxTabWidth > 0 && getMeasuredWidth() > maxTabWidth) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(maxTabWidth, MeasureSpec.EXACTLY),
                    heightMeasureSpec);
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setMaxTabWidth(int maxTabWidth) {
        this.maxTabWidth = maxTabWidth;
    }

    public void setTitle(CharSequence text) {
        mTabTitle.setText(text);
    }

    public void setImage(int resId) {
        mTabIcon.setImageResource(resId);
    }
}
