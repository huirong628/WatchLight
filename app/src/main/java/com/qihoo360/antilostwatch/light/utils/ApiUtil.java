package com.qihoo360.antilostwatch.light.utils;

import java.util.Calendar;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 */

public class ApiUtil {
    public static String getTimestamp() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000); // 时间戳（单位秒）
    }

    public static String getM2() {
        return "add72710293144865e2d8c053bf3b28a";
    }

    public static String decryptResult(byte[] result) throws Exception {
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

    protected static String getRc4Key() {
        return getEncryptKey();
    }

    public static String getEncryptKey() {
        String token = "";
        String qid = "";
        String rc4key = MD5Utils.encode(token + MD5Utils.encode(qid));
        return rc4key;
    }
}
