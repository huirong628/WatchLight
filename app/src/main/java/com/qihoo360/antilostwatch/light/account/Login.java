package com.qihoo360.antilostwatch.light.account;


import com.qihoo360.antilostwatch.light.api.ApiService;
import com.qihoo360.antilostwatch.light.response.GetIpInfoResponse;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class Login extends AccountOperate {

    public Login(String name, String pwd) {

    }

    public void doLogin() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://passport.360.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getIpInfo("63.223.108.42")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetIpInfoResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GetIpInfoResponse getIpInfoResponse) {
                System.out.println("here");
            }
        });
    }
}
