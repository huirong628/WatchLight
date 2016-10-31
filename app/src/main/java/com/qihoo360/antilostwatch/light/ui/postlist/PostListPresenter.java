package com.qihoo360.antilostwatch.light.ui.postlist;

import android.text.TextUtils;
import android.util.Base64;

import com.qihoo360.antilostwatch.light.base.BaseCommonPresenter;
import com.qihoo360.antilostwatch.light.data.bean.PostList;
import com.qihoo360.antilostwatch.light.utils.EncryptRC4;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

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
        //处理token 和 p

        String token = "";
        String p = getURLEncoder(encryptRc4());
        mApiWrapper.loadPostList(token, p, new Observer<PostList>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError(),e.getMessage() = " + e.getMessage());
            }

            @Override
            public void onNext(PostList postList) {
                mView.onPostListLoaded(postList);
                System.out.println("onNext(),retcode = " + postList.getRetCode() + ",errcode = " + postList.getErrCode());
            }
        });
    }

    private String encryptRc4() {
        return encryptRc4(getEncodeParam());
    }

    public static String encryptRc4(String source) {
        return encryptRc4(source, getEncryptKey());
    }

    public static String encryptRc4(String source, String rc4key) {
        if (TextUtils.isEmpty(source)) {
            return "";
        }
        byte[] result = null;
        try {
            byte[] data = source.getBytes("UTF-8");
            result = EncryptRC4.make(rc4key, data);
        } catch (Exception e) {
        }
        String base64para = null;
        if (result != null) {
            base64para = new String(Base64.encode(result, Base64.NO_WRAP));
        }
        return base64para;
    }

    public static String getEncryptKey() {
        String token = "";
        String qid = "";
        String rc4key = MD5Utils.encode(token + MD5Utils.encode(qid));
        return rc4key;
    }

    private String getEncodeParam() {
        String timestamp = getTimestamp();
        String m2 = "add72710293144865e2d8c053bf3b28a";
        String source = "timestamp=" + getURLEncoder(timestamp) + "&" + "m2=" + getURLEncoder(m2);
        return source;
    }

    public static String getTimestamp() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000); // 时间戳（单位秒）
    }

    public static String getURLEncoder(String content) {
        if (content == null || content.length() == 0) {
            return content;
        }
        try {
            content = URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return content;
    }
}