package com.qihoo360.antilostwatch.light.ui.postlist;

import com.qihoo360.antilostwatch.light.base.BaseCommonPresenter;
import com.qihoo360.antilostwatch.light.data.model.PostList;

import rx.Observer;


/**
 * Created by HuirongZhang on 2016/10/26.
 */

public class PostListPresenter extends BaseCommonPresenter<PostListFragment> implements PostListContract.Presenter {

    public PostListPresenter(PostListFragment view) {
        super(view);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void loadPostList() {
        //向服务端发送请求，获取数据，更新UI。
        mApiWrapper.loadPostList(new Observer<PostList>() {

            @Override
            public void onCompleted() {
                System.out.println("onCompleted()");
            }


            @Override
            public void onError(Throwable e) {
                System.out.println("onError(),e =" + e.getMessage());
            }


            @Override
            public void onNext(PostList postList) {
                System.out.println("onNext()");
            }
        });

        /*OkHttpClient okHttpClient = new OkHttpClient();
        String time = String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000);
        String m2 = "add72710293144865e2d8c053bf3b28a";
        final Request request = new Request.Builder()
                .url("https://m.baby.360.cn/talk/recommend/more?token=&m2=add72710293144865e2d8c053bf3b28a")
                .build();
        request.header("kids-Android");
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure,e = " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse(),response = " + response.body().toString());
            }
        });*/
    }
}