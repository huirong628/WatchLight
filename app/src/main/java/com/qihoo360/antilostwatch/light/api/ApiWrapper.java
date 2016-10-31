package com.qihoo360.antilostwatch.light.api;


import com.qihoo360.antilostwatch.light.data.bean.PostList;
import com.qihoo360.antilostwatch.light.utils.EncryptRC4;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HuirongZhang on 2016/10/25.
 * <p>
 * 主要用到rx
 */

public class ApiWrapper extends Api {

    public void loadPostList(Observer<PostList> observer) {
        // applySchedulers(getApiService().loadPostList(), observer);

       /* Call<ResponseBody> call = getApiService().loadPostList();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println("APi*******" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });*/
    }

    public static <T> void applySchedulers(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void loadPostList(String token, String p) {
        Call<ResponseBody> call = getApiService().loadPostList(token, p);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("onResponse(),call.request() = " + call.request().toString());
                System.out.println("onResponse(),call.request().headers() = " + call.request().headers().toString());

                if (response.isSuccessful()) {
                    System.out.println("onResponse(),response.code() = " + response.code());
                    System.out.println("onResponse(),response.headers() = " + response.headers());
                    System.out.println("onResponse(),response.message() = " + response.message());
                    System.out.println("onResponse(),response.body() = " + response.body());
                    System.out.println("onResponse(),response.errorBody() = " + response.errorBody());
                    System.out.println("onResponse(),response.raw() = " + response.raw());
                    try {
                        byte[] result = response.body().bytes();
                        String decryptResult = decryptResult(result);
                        System.out.println("decryptResult = " + decryptResult);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("onFailure()" + t.getLocalizedMessage());
            }
        });
    }

    protected String decryptResult(byte[] result) throws Exception {
        String resultStr = decryptResultFromServer(result, getRc4Key());
        return resultStr;
    }

    public static String decryptResultFromServer(byte[] result, String rc4Key) throws Exception {
        if (result == null) {
            return null;
        }
        String jsonStr = "";
        String temp = "";
        temp = new String(result, "UTF-8");
        temp = temp.trim();
        if (temp.startsWith("{") && temp.endsWith("}")) {
            jsonStr = temp;
        } else if (temp.startsWith("<html>") && temp.endsWith("</html>")) {
            jsonStr = "";
        } else {
            jsonStr = decryptResult(result, rc4Key);
        }
        return jsonStr;
    }

    public static String decryptResult(byte[] result, String rc4key) {
        if (result == null) {
            return "";
        }
        String dataStr = "";
        try {
            byte[] data = EncryptRC4.make(rc4key, result);
            dataStr = new String(data, "UTF-8");
        } catch (Exception e) {
        }
        return dataStr;
    }

    protected String getRc4Key() {
        return getEncryptKey();
    }

    public static String getEncryptKey() {
        String token = "";
        String qid = "";
        String rc4key = MD5Utils.encode(token + MD5Utils.encode(qid));
        return rc4key;
    }
}