package com.qihoo360.antilostwatch.light.api.converter;

import com.google.gson.Gson;
import com.qihoo360.antilostwatch.light.utils.EncryptRC4;
import com.qihoo360.antilostwatch.light.utils.MD5Utils;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by HuirongZhang on 2016/10/30.
 */

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson mGson;
    private final Type mType;

    public JsonResponseBodyConverter(Gson gson, Type type) {
        this.mGson = gson;
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        byte[] bytes = responseBody.bytes();
        String json = null;
        try {
            json = decryptResult(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mGson.fromJson(json, mType);
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
