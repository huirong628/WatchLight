package com.qihoo360.antilostwatch.light.api.converter;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
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

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new JsonRequestBodyConverter<>();
    }

    /**
     * 这里用于对Field、FieldMap、Header、Path、Query、QueryMap注解的处理
     *
     * Retrfofit对于上面的几个注解默认使用的是调用toString方法
     *
     * @param type
     * @param annotations
     * @param retrofit
     * @return
     */
    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return null;//new StringRequestBodyConverter<>();
    }
}
