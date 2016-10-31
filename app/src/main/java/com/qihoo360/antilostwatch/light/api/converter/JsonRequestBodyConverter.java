package com.qihoo360.antilostwatch.light.api.converter;


import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by HuirongZhang on 2016/10/30.
 */

public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    public JsonRequestBodyConverter() {

    }

    @Override
    public RequestBody convert(T value) throws IOException {

        return null;
    }
}
