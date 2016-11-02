package com.qihoo360.antilostwatch.light.ui.topiclist;

import com.qihoo360.antilostwatch.light.base.BasePresenter;
import com.qihoo360.antilostwatch.light.base.BaseView;

/**
 * Created by HuirongZhang on 2016/11/2.
 */

public interface TopicListContract {
    interface View extends BaseView {
        void onTopicListLoaded();
    }

    interface Presenter extends BasePresenter {
        void loadTopicList();
    }
}
