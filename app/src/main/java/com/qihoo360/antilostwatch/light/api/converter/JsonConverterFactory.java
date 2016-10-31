package com.qihoo360.antilostwatch.light.api.converter;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by HuirongZhang on 2016/10/30.
 */

public class JsonConverterFactory extends Converter.Factory {

    private final Gson mGson;

    public JsonConverterFactory(Gson gson) {
        this.mGson = gson;
    }

    public static JsonConverterFactory create() {
        return create(new Gson());
    }

    private static JsonConverterFactory create(Gson gson) {
        return new JsonConverterFactory(gson);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new JsonResponseBodyConverter<>(mGson); //响应
    }
}
