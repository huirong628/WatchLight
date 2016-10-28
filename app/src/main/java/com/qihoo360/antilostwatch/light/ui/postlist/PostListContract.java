package com.qihoo360.antilostwatch.light.ui.postlist;

import com.qihoo360.antilostwatch.light.base.BasePresenter;
import com.qihoo360.antilostwatch.light.base.BaseView;
import com.qihoo360.antilostwatch.light.data.model.Post;

import java.util.List;

/**
 * Created by HuirongZhang on 2016/10/26.
 */

public interface PostListContract {
    interface View extends BaseView {
        void onPostListLoaded(List<Post> postList);
    }

    interface Presenter extends BasePresenter {
        void loadPostList();
    }
}
