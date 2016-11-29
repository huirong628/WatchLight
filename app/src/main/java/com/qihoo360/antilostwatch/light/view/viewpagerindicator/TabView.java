package com.qihoo360.antilostwatch.light.view.viewpagerindicator;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by HuirongZhang
 * on 2016/11/29.
 */

public class TabView extends TextView {
    public int index;

    public TabView(Context context) {
        super(context);
    }

    public int getIndex() {
        return index;
    }
}
