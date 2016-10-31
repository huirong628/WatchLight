package com.qihoo360.antilostwatch.light.api.converter;

import android.text.TextUtils;
import android.util.Base64;

import com.qihoo360.antilostwatch.light.utils.EncryptRC4;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import retrofit2.Converter;

/**
 * Created by HuirongZhang on 2016/10/31.
 */

public class StringRequestBodyConverter<T> implements Converter<T, String> {

    @Override
    public String convert(T value) throws IOException {

        return null;
    }
}