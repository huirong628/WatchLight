package com.qihoo360.antilostwatch.light.view.viewpagerindicator;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by HuirongZhang
 * on 2016/11/29.
 */

public class TabView extends TextView {
    private int index;
    private int maxTabWidth;

    public TabView(Context context) {
        super(context);
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
}
